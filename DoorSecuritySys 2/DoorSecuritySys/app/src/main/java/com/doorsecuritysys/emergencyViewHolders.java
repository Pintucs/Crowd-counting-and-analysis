package com.doorsecuritysys;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class emergencyViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView date;
    public ImageView emergencyIcon;
    public LinearLayout ll1;
    RecyclerView.Adapter a;
    Emergency d;
    List<emergencyObject> dItem;
    public emergencyViewHolders(View itemView, RecyclerView.Adapter a, List<emergencyObject> dItem,Emergency d) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.a=a;
        this.d=d;
        this.dItem=dItem;
        date = (TextView) itemView.findViewById(R.id.msg2);
        emergencyIcon = (ImageView) itemView.findViewById(R.id.type);
        ll1=(LinearLayout) itemView.findViewById(R.id.ll1);
        //set default selection of first type vehicle for ride
//        vehicleObject vehicle=dItem.get(0);
//        DailyRideFragment.vehicletype=vehicle.getVehicleName();
//        Log.e("adaptertest","adaptertet");
//        //d.getNearbyVehicle();
    }


    @Override
    public void onClick(View v) {

        try {
            int pos = this.getAdapterPosition();
            emergencyObject emergency=dItem.get(pos);
            d.get_Emergency_Zoom();
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            Log.e("Vehicleadapetr",e.getMessage());
        }

    }
}
