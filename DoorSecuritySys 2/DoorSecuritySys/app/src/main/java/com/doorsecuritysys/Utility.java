package com.doorsecuritysys;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import static android.content.Context.MODE_PRIVATE;

public class Utility {


    public static Dialog LoadingSpinner(Context mContext){
        Dialog pd = new Dialog(mContext, android.R.style.Theme_Black);
        View view = LayoutInflater.from(mContext).inflate(R.layout.progress, null);
        pd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pd.getWindow().setBackgroundDrawableResource(R.color.transparent);
        pd.setContentView(view);
        return pd;
    }
    public static void clearPreferenceData(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

    public static void addPreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }


    public static void addPreferences(Context context, String key, Boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static String getPreferences(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", MODE_PRIVATE);
        String text = prefs.getString(key, "");
        return text;
    }

    public static Double getPreferences(Context context, String key, Double defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", MODE_PRIVATE);
        String text = prefs.getString(key, "");
        if (text.equals(""))
            return defaultValue;
        return Double.valueOf(text);
    }

    public static int getPreferences(Context context, String key, int defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", MODE_PRIVATE);
        String text = prefs.getString(key, "");
        if (text.equals(""))
            return defaultValue;
        return Integer.parseInt(text);
    }

    public static Boolean getPreferences(Context context, String key, boolean defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", MODE_PRIVATE);
        Boolean text = prefs.getBoolean(key, defaultValue);
        return text;
    }

}
