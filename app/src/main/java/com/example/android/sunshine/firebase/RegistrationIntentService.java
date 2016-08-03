package com.example.android.sunshine.firebase;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.android.sunshine.MainActivity;
import com.example.android.sunshine.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by T510 Owner on 7/25/2016.
 */
public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            synchronized (TAG) {



                FirebaseInstanceId instanceID = FirebaseInstanceId.getInstance(FirebaseApp.zzeh(this));







                String senderId = getString(R.string.firebase_defaultSenderId);
                if ( senderId.length() != 0 ) {
                    String token = instanceID.getToken(senderId, FirebaseMessaging.INSTANCE_ID_SCOPE);

                    sendRegistrationToServer(token);
                }

                sharedPreferences.edit().putBoolean(MainActivity.SENT_TOKEN_TO_SERVER, true).apply();
            }
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);

            sharedPreferences.edit().putBoolean(MainActivity.SENT_TOKEN_TO_SERVER, false).apply();
        }

    }

    private void sendRegistrationToServer(String token) {
        Log.i(TAG, "Firebase Registration Token: " + token);
    }
}
