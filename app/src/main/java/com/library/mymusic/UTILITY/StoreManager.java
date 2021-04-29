package com.library.mymusic.UTILITY;

import android.content.Context;
import android.content.SharedPreferences;

class StoreManager {
    private SharedPreferences.Editor editor;
    public SharedPreferences pref;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "opencart_Store";


    public StoreManager(Context _context) {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void setLoginStatus(Boolean Islogin) {
        editor.putBoolean("LoginValue", Islogin);
        System.out.println("Login Status" + " " + Islogin);
        editor.commit();
    }

    public Boolean getLoginStatus() {
        return pref.getBoolean("LoginValue", false);
    }

}
