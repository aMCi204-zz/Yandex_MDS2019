package com.amciof.Yandex_MDS2019;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class SPHelper {

    public static final String WAS_STARTED = "was_started";

    public static final String THEME = "theme";

    public static final String LIGHT  = "light";
    public static final String DARK   = "dark";

    public static final String MODEL = "model";

    public static final String STANDART   = "standart";
    public static final String DENSE      = "dense";

    public static final String SORT = "sort";

    public static final String ASCENDING_BY_LABEL     = "ascending_by_label";
    public static final String ASCENDING_BY_DATE      = "ascending_by_date";
    public static final String ASCENDING_BY_LAUNCHES  = "ascending_by_launches";
    public static final String DESCENDING_BY_LABEL    = "descending_by_label";
    public static final String DESCENDING_BY_DATE     = "descending_by_date";
    public static final String DESCENDING_BY_LAUNCHES = "descending_by_launches";

    /**
     * Set a string shared preference
     * @param key - Key to set shared preference
     * @param value - Value for the key
     */
    public static void putString(Context context, String key, String value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Set a integer shared preference
     * @param key - Key to set shared preference
     * @param value - Value for the key
     */
    public static void putInt(Context context, String key, int value){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Set a Boolean shared preference
     * @param key - Key to set shared preference
     * @param value - Value for the key
     */
    public static void putBoolean(Context context, String key, boolean value){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Get a string shared preference
     * @param key - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public static String getString(Context context, String key, String defValue){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

        return settings.getString(key, defValue);
    }

    /**
     * Get a integer shared preference
     * @param key - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public static int getInt(Context context, String key, int defValue){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

        return settings.getInt(key, defValue);
    }

    /**
     * Get a boolean shared preference
     * @param key - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public static boolean getBoolean(Context context, String key, boolean defValue){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

        return settings.getBoolean(key, defValue);
    }

}
