package com.mdgiitr.karthik.cognizance19.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private SharedPreferences prefs;

    public PreferenceHelper(Context context) {
        prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    public String getToken() {
        return prefs.getString("token", "");
    }

    public void setToken(String token) {
        prefs.edit().putString("token", token).apply();
    }

    public boolean getLoginStatus() {
        return prefs.getBoolean("loginStatus", false);
    }

    public void setLoginStatus(boolean status) {
        prefs.edit().putBoolean("loginStatus", status).apply();
    }

    public boolean getStoragePerm() {
        return prefs.getBoolean("storage_perm", false);
    }

    public void setStoragePerm(boolean p) {
        prefs.edit().putBoolean("storage_perm", p).apply();
    }

    public void clearSharedPrefs() {
        prefs.edit().clear().apply();
    }

    public Integer getUserType() {
        return prefs.getInt("user_type", -1);
    }

    public void setUserType(int i) {
        prefs.edit().putInt("user_type", i).apply();
    }

    public String getCogniId() {
        return prefs.getString("cogni_id", "");
    }

    public void setCogniId(String id) {
        prefs.edit().putString("cogni_id", id).apply();
    }
}
