package com.doorsecuritysys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;



public class VolleyApi {

    private ProgressDialog mProgressDialog;
    private static VolleyApi volleyApi = null;
    private ResponseListener mlistener_response;
    public static final String BASE_URL = "http://10.0.2.2:8888/HSAD_webservices/";

    public static final String BASE_Image_URL = "http://10.0.2.2:8888/HSAD_webservices/profile_pic/";


    public static VolleyApi getInstance() {

        if (volleyApi != null)
            return volleyApi;
        else
            return new VolleyApi();
    }


    public interface ResponseListener {

        void _onResponseError(Throwable e);

        void _onNext(String obj);

        void _onNext1(String obj);

        void _onVollyError(Exception e);

    }


    //----------------------------------------------- Volly Api-------------------------------------------------------


    //----------------------------------------------- Login Api-------------------------------------------------------


    public void userLogin(final Activity activity, final ResponseListener _mlistener_response, final String email, final String password ) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "login.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("uid", email);
                    params.put("password", password);
                    return params;
                }


            };

            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "ok", Toast.LENGTH_SHORT).show();

        }
    }




    //----------------------------------------------- Delete door data Api-------------------------------------------------------


    public void delete(final Activity activity, final ResponseListener _mlistener_response,
                             final String id) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "door_status.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();

                            mlistener_response._onNext1
                                    (response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("status", id);
                    return params;
                }


            };

            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "ok", Toast.LENGTH_SHORT).show();

        }
    }


    //----------------------------------------------- Get Door Data Api-------------------------------------------------------


    public void getDoorData(final Activity activity, final ResponseListener _mlistener_response) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "door_data.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    return params;
                }


            };

            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "ok", Toast.LENGTH_SHORT).show();

        }
    }


}


