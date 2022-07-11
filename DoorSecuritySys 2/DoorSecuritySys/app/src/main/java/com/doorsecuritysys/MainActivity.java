package com.doorsecuritysys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.doorsecuritysys.VolleyApi.BASE_Image_URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,VolleyApi.ResponseListener {
    Button btCheck;
    RelativeLayout ll;
    ImageView imageView;
    LinearLayout reject,tick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btCheck = (Button)findViewById(R.id.btCheck);
        btCheck.setOnClickListener(this);
        ll = (RelativeLayout)findViewById(R.id.ll);
        imageView = (ImageView)findViewById(R.id.imageView);
        reject= (LinearLayout)findViewById(R.id.reject);
        tick= (LinearLayout)findViewById(R.id.llTick);
        reject.setOnClickListener(this);
        tick.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btCheck:
                getData();
                break;
            case R.id.reject:
                //Toast.makeText(this, "reject", Toast.LENGTH_SHORT).show();
                delete("0");
                break;
            case R.id.llTick:
                delete("1");
                //Toast.makeText(this, "accept", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void delete(String status) {
        VolleyApi.getInstance().delete(this,this, status);
    }

    private void getData() {
        VolleyApi.getInstance().getDoorData(this,this);
    }

    @Override
    public void _onResponseError(Throwable e) {

    }

    @Override
    public void _onNext(String obj) {
        try {
            String name="",date="";

            JSONObject obj1 = new JSONObject(obj);
            JSONArray jArray = obj1.getJSONArray("door_data");
            //int len = jArray.length();
            for (int i = 0; i < jArray.length(); i++) {

                JSONObject json_data = jArray.getJSONObject(i);

                name= json_data.getString("img");
                date= json_data.getString("date_time");


            }

            ll.setVisibility(View.VISIBLE);
            Picasso.with(this)
                    .load(BASE_Image_URL+""+name).fit()
                    .into(imageView);




        } catch (JSONException e) {
            e.printStackTrace();
            ll.setVisibility(View.GONE);
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void _onNext1(String obj) {
        try {
            String name="",date="";

            JSONObject obj1 = new JSONObject(obj);
            JSONArray jArray = obj1.getJSONArray("door_status");
            //int len = jArray.length();
            for (int i = 0; i < jArray.length(); i++) {

                JSONObject json_data = jArray.getJSONObject(i);




            }

            ll.setVisibility(View.GONE);




        } catch (JSONException e) {
            e.printStackTrace();


        }


    }

    @Override
    public void _onVollyError(Exception e) {

    }
}
