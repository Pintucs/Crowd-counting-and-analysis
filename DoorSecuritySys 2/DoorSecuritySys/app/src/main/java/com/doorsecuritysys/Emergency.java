package com.doorsecuritysys;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.doorsecuritysys.VolleyApi.BASE_Image_URL;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Emergency.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Emergency#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Emergency extends Fragment implements VolleyApi.ResponseListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    private Dialog progress_spinner;
    private OnFragmentInteractionListener mListener;
    List<emergencyObject> emergencyArray;
    public Emergency() {
        // Required empty public constructor
        emergencyArray=new ArrayList<>();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Emergency.
     */
    // TODO: Rename and change types and number of parameters
    public static Emergency newInstance(String param1, String param2) {
        Emergency fragment = new Emergency();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_emergency, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.emergency_recyclerview);

        progress_spinner=Utility.LoadingSpinner(getContext());
        progress_spinner.show();
        try {
            getEmergency();
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void getEmergency()
    {
        VolleyApi.getInstance().getDoorData(getActivity(),this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void _onResponseError(Throwable e) {

    }

    @Override
    public void _onNext(String obj) {
        try {
            progress_spinner.dismiss();
            String name="",date="",id="";

            JSONObject obj1 = new JSONObject(obj);
            JSONArray jArray = obj1.getJSONArray("door_data");
            //int len = jArray.length();
            for (int i = 0; i < jArray.length(); i++) {

                JSONObject json_data = jArray.getJSONObject(i);

                name= json_data.getString("img");
                date= json_data.getString("date_time");
                id= json_data.getString("id");
                emergencyObject emergencyObject=new emergencyObject(date,name,id);
                emergencyArray.add(emergencyObject);

            }

            emergencyAdapter adp=new emergencyAdapter(getActivity().getApplicationContext(),emergencyArray,this);
            recyclerView.setAdapter(adp);
           /* ll.setVisibility(View.VISIBLE);
            Picasso.with(this)
                    .load(BASE_Image_URL+""+name).fit()
                    .into(imageView);


*/

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "No Data Found", Toast.LENGTH_SHORT).show();

        }
    }

    public void get_Emergency_Zoom()
    {
        Toast.makeText(getActivity().getApplicationContext(),"Working",Toast.LENGTH_LONG).show();
    }

    @Override
    public void _onNext1(String obj) {

    }

    @Override
    public void _onVollyError(Exception e) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
