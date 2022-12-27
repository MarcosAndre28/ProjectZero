package com.example.projectzero.utils;

import android.content.Context;
import android.widget.Toast;

import org.mindrot.jbcrypt.BCrypt;

import java.security.MessageDigest;

public class Utils {

    private static Toast mToast;

    public static void showShort(Context context, String message) {
        if (mToast != null) {
            mToast.cancel();
        } else {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            mToast.show();
        }
    }

    public static void showLong(Context context, String message) {
        if (mToast != null) {
            mToast.cancel();
        } else {
            mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            mToast.show();
        }
    }

    public static String hashPassword(String password){
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
