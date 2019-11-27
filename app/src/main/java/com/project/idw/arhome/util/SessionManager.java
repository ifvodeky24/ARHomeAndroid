package com.project.idw.arhome.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

public class SessionManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String NAMA_LENGKAP = "nama_lengkap";
    public static final String ALAMAT = "alamat";
    public static final String FOTO = "foto";
    public static final String NO_HANDPHONE = "no_handphone";
    public static final String LEVEL_LOGIN = "level_login";
    public static final String LEVEL_PEMILIK = "level_pemilik";
    public static final String LEVEL_PENGGUNA = "level_pengguna";

    public Context getContext(){
        return context;
    }

    //Constructor
    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(String id, String username,  String password, String email, String nama_lengkap, String alamat, String foto, String no_handphone, String level_login){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(ID, id);
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, password);
        editor.putString(EMAIL, email);
        editor.putString(NAMA_LENGKAP, nama_lengkap);
        editor.putString(ALAMAT, alamat);
        editor.putString(FOTO, foto);
        editor.putString(NO_HANDPHONE, no_handphone);
        editor.putString(LEVEL_LOGIN, level_login);
        editor.commit();
    }

    public HashMap<String, String> getLoginDetail(){
        HashMap<String, String> user= new HashMap<>();
        user.put(ID, sharedPreferences.getString(ID, null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(PASSWORD, sharedPreferences.getString(PASSWORD, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(NAMA_LENGKAP, sharedPreferences.getString(NAMA_LENGKAP, ""));
        user.put(ALAMAT, sharedPreferences.getString(ALAMAT, null));
        user.put(FOTO, sharedPreferences.getString(FOTO, null));
        user.put(NO_HANDPHONE, sharedPreferences.getString(NO_HANDPHONE, null));
        user.put(LEVEL_LOGIN, sharedPreferences.getString(LEVEL_LOGIN, null));

        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

}
