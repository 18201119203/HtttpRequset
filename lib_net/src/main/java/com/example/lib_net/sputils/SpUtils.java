package com.example.lib_net.sputils;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;

import com.example.lib_net.BaseApp;

public class SpUtils {

    private static volatile SpUtils sp;
    private String name = "bw_DATA";
    private SpUtils() {

    }

    public static SpUtils initSpUtils(){

        if (sp==null){
            synchronized (SpUtils.class){
                if (sp==null){
                    sp = new SpUtils();
                }
            }
        }
        return sp;
    }

    public SharedPreferences getSp(){

        SharedPreferences sharedPreferences = BaseApp.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        if (sharedPreferences!=null){
            return sharedPreferences;
        }
        return null;

    }

    public void putStringSp(String key,String value){
        getSp().edit().putString(key,value);
    }

    public String getStringSp(String key){
        return getSp().getString(key,"1");
    }
    public boolean getbooleanSp(String key){
        return getSp().getBoolean(key,false);
    }

}
