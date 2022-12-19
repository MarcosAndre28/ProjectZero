package com.example.projectzero.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import java.security.MessageDigest;

public class Utilites {

    private static Toast mToast;

    public static void showShort(Context context, String message){
        if (mToast != null){
            mToast.cancel();
        }
        else {
        mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        mToast.show();
        }
    }

    public static void showLong(Context context, String message){
        if (mToast != null){
            mToast.cancel();
        }
        else {
            mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            mToast.show();
        }
    }
    // Senha Criptografada
    public static String hashPassword(String password) throws Exception {
        // Obtém uma instância da função de hash SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        // Calcula o hash da senha
        byte[] hash = digest.digest(password.getBytes("UTF-8"));
        // Converte o hash para uma string hexadecimal
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
