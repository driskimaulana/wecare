package com.kelompok4.wecare.viewmodel.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessaging extends FirebaseMessagingService {

    String TAG = "mydebug";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        Log.d(TAG, "From + " + message.getFrom());

        // cek apakah message punya data dalam payload
        if (message.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + message.getData());
        }

        // check if there is notification payloadd
        if (message.getNotification() != null) {
            Log.d(TAG, "Message notification body: " + message.getNotification().getBody());
        }
    }
}
