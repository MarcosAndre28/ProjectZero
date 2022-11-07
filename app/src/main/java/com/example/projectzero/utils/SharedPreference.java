package com.example.projectzero.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.projectzero.db.model.User;
import com.google.gson.Gson;

public class SharedPreference {
    private SharedPreferences sharedPreference;

    public SharedPreference(Context context) {
        sharedPreference = context.getSharedPreferences("User_data_pref", Context.MODE_PRIVATE);
    }

    public void saveLoggedInUser(User user) {
        SharedPreferences.Editor prefsEditor = sharedPreference.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("user", json);
        prefsEditor.apply();
    }

    public User retrieveLoggedInUser() {
        Gson gson = new Gson();
        String json = sharedPreference.getString("user", "");
        return gson.fromJson(json, User.class);
    }

    public void removeUser() {
        SharedPreferences.Editor prefsEditor = sharedPreference.edit();
        prefsEditor.clear().apply();
    }
}
