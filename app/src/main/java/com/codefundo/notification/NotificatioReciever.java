package com.codefundo.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificatioReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg=intent.getStringExtra("toastmessage");
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
