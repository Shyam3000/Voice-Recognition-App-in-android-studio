package com.example.voicerecognisation;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class OfflineFirebaseData extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
