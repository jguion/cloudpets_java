package com.parse;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.spiraltoys.cloudpets2.events.VoiceMessagePushNotificationReceivedEvent;
import de.greenrobot.event.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

public class CloudPetsPushBroadcastReceiver extends ParsePushBroadcastReceiver {
    public static final int NOTIFICATION_ID = "CLOUD_PETS".hashCode();

    protected void onPushReceive(Context context, Intent intent) {
        JSONObject pushData = null;
        try {
            pushData = new JSONObject(intent.getStringExtra(ParsePushBroadcastReceiver.KEY_PUSH_DATA));
        } catch (JSONException ex) {
            PLog.e("com.parse.ParsePushReceiver", "Unexpected JSONException when receiving push data: ", ex);
        }
        String action = null;
        if (pushData != null) {
            action = pushData.optString("action", null);
        }
        if (action != null) {
            Bundle notification = intent.getExtras();
            Intent broadcastIntent = new Intent();
            broadcastIntent.putExtras(notification);
            broadcastIntent.setAction(action);
            broadcastIntent.setPackage(context.getPackageName());
            context.sendBroadcast(broadcastIntent);
        }
        Notification notification2 = getNotification(context, intent);
        if (notification2 != null) {
            synchronized (ParseNotificationManager.getInstance()) {
                ParseNotificationManager.getInstance().setShouldShowNotifications(false);
                ParseNotificationManager.getInstance().showNotification(context, notification2);
                ParseNotificationManager.getInstance().setShouldShowNotifications(true);
            }
            showNotification(context, notification2);
            EventBus.getDefault().post(new VoiceMessagePushNotificationReceivedEvent());
        }
    }

    public void showNotification(Context context, Notification notification) {
        if (context != null && notification != null) {
            NotificationManager nm = (NotificationManager) context.getSystemService("notification");
            try {
                nm.notify(NOTIFICATION_ID, notification);
            } catch (SecurityException e) {
                notification.defaults = -1;
                nm.notify(NOTIFICATION_ID, notification);
            }
        }
    }
}
