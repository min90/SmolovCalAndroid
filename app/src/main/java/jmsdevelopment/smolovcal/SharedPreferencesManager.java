package jmsdevelopment.smolovcal;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jesper on 01/07/2016.
 */

public class SharedPreferencesManager {

    private static final String SHARED_TAG = "snake_eyes_shared_prefs";
    private static final String VERSION_TAG = "version";
    private static final String REMEMBER_ME_TAG = "remember_me";

    private static SharedPreferencesManager instance;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private SharedPreferencesManager(Context context) {
        preferences = context.getSharedPreferences(SHARED_TAG, 0);
        editor = preferences.edit();
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesManager(context);
        }
    }

    public static SharedPreferencesManager get() {
        return instance;
    }

    public void setVersionName(String version){
        editor.putString(VERSION_TAG, "Version: " + version).commit();
    }

    public String getVersionName(){
        return preferences.getString(VERSION_TAG, "");
    }

    public void setRememberMe(boolean rememberMe){
        editor.putBoolean(REMEMBER_ME_TAG, rememberMe).commit();
    }

    public boolean getRememberMe(){
        return preferences.getBoolean(REMEMBER_ME_TAG, false);
    }
}
