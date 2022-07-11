package com.doorsecuritysys;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;



public class emergencyAdapter extends RecyclerView.Adapter<emergencyViewHolders>{

    public static List<emergencyObject> dataItem;
    private Context mContext;
    private LayoutInflater inflater;
    private Emergency d;


    public emergencyAdapter(Context mContext, List<emergencyObject> dataItem,Emergency d) {
        this.mContext = mContext;
        inflater= LayoutInflater.from(mContext);
        this.dataItem = dataItem;
        this.d=d;
    }

    @Override
    public void onBindViewHolder(emergencyViewHolders holder, int position) {

        emergencyObject c = dataItem.get(position);

        holder.date.setText(c.getEmergencyName());

        Picasso.with(this.mContext).load(VolleyApi.BASE_URL+c.getEmergencyIcon()).into(holder.emergencyIcon);

    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }



    @Override
    public emergencyViewHolders onCreateViewHolder(final ViewGroup parent, int viewType) {
        Log.e("justtest","justtest");
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_emergencylog,parent, false);


        return new emergencyViewHolders(v,this,dataItem,d);
    }
}



