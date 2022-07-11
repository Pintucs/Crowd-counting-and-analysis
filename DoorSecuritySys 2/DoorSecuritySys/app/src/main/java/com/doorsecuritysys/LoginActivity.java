package com.doorsecuritysys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements VolleyApi.ResponseListener{

    EditText email,password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        email = (EditText)findViewById(R.id.login_etEmail);
        password = (EditText)findViewById(R.id.login_etPassword);
        login = (Button)findViewById(R.id.login_tvLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()){
                    process_login();
                }
            }
        });



    }

    private void process_login() {
        VolleyApi.getInstance().userLogin(this,this,email.getText().toString().trim(),password.getText().toString().trim());
    }

    boolean checkValidation(){
        boolean ret = true;
        if(!Validation.hasText(password))ret=false;
        if(!Validation.isPhoneNumber(email,true))ret=false;

        return ret;
    }

    @Override
    public void _onResponseError(Throwable e) {
        
    }

    @Override
    public void _onNext(String obj) {
        try {

            String name = "";
            JSONObject obj1 = new JSONObject(obj);
            JSONArray jArray = obj1.getJSONArray("login");
            //int len = jArray.length();
            for (int i = 0; i < jArray.length(); i++) {

                JSONObject json_data = jArray.getJSONObject(i);

                Utility.addPreferences(LoginActivity.this,Constants.keyUserEmail,json_data.getString("email"));
                Utility.addPreferences(LoginActivity.this,Constants.keyUserFirstName,json_data.getString("name"));
                Utility.addPreferences(LoginActivity.this,Constants.keyUserMobileNo,json_data.getString("contact"));
                Utility.addPreferences(LoginActivity.this,Constants.keyUserGender,json_data.getString("gender"));
                Utility.addPreferences(LoginActivity.this,Constants.keyUserId,json_data.getString("id"));
                Utility.addPreferences(LoginActivity.this,Constants.keyUserPic,json_data.getString("pic"));
                Utility.addPreferences(LoginActivity.this,Constants.keyLoginCheck,true);
            }
            Toast.makeText(this, name+" login sucessfully", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            finish();
          

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void _onNext1(String obj) {

    }

    @Override
    public void _onVollyError(Exception e) {

    }
}
