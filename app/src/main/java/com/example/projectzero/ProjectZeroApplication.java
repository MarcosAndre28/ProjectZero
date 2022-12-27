package com.example.projectzero;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.projectzero.db.AppDatabase;

public class ProjectZeroApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
