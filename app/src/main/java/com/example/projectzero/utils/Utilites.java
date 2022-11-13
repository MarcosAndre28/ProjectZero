package com.example.projectzero.utils;

import android.content.Context;
import android.widget.Toast;

public class Utilites {
  public static Toast initializeToast(Context context, String res){
      return Toast.makeText(context, res, Toast.LENGTH_SHORT);
  }
}
