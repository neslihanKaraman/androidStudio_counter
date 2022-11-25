package com.example.number_counter;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesClass {
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    private static SharedPreferencesClass sharedPreferencesClass=null;

    private SharedPreferencesClass(Context context){
        sharedPreferences=context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    public static SharedPreferencesClass getInstance(Context context){
        if(sharedPreferencesClass== null)
            sharedPreferencesClass=new SharedPreferencesClass(context);
        return sharedPreferencesClass;
    }
    public static int getCounterValue(){ return sharedPreferences.getInt("counterValue",0); }
    public static void setCounterValue(int value){
        editor.putInt("counterValue",value);
        editor.apply();
    }
    public static int getUpLimitValue(){ return sharedPreferences.getInt("upLimitValue",0); }
    public static void setUpLimitValue(int value){
        editor.putInt("upLimitValue",value);
        editor.apply();
    }
    public static int getDownLimitValue(){ return sharedPreferences.getInt("downLimitValue",0); }
    public static void setDownLimitValue(int value){
        editor.putInt("downLimitValue",value);
        editor.apply();
    }
    public static boolean getUpVib(){ return sharedPreferences.getBoolean("upperVib",true);}
    public static void setUpVib(boolean b){
        editor.putBoolean("upperVib",b);
        editor.apply();
    }
    public static boolean getUpSound(){ return sharedPreferences.getBoolean("upperSound",true);}
    public static void setUpSound(boolean b){
        editor.putBoolean("upperSound",b);
        editor.apply();
    }
    public static boolean getDownSound(){ return sharedPreferences.getBoolean("downSound",true);}
    public static void setDownSound(boolean b){
        editor.putBoolean("downSound",b);
        editor.apply();
    }
    public static boolean getDownVib(){ return sharedPreferences.getBoolean("downVib",true);}
    public static void setDownVib(boolean b){
        editor.putBoolean("downVib",b);
        editor.apply();
    }

}
