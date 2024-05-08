package com.daniel.linenotifywizard;

import android.app.Notification;
import android.app.Service;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class NotificationListener extends NotificationListenerService {
    @Override
    public void onListenerConnected() {

        super.onListenerConnected();
        Log.e("LNW", "onListenerConnected");
    }

    /**
     * Implement this method to learn about new notifications as they are posted by apps.
     *
     * @param sbn A data structure encapsulating the original {@link Notification}
     *            object as well as its identifying information (tag and id) and source
     *            (package name).
     */
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        if(sbn.getPackageName().compareTo("jp.naver.line.android") == 0) {
            Log.e("LNW", sbn.toString());
            Log.e("LNW", (String) sbn.getNotification().tickerText);
            String title = (String) sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TITLE);
            if(title == null) {
                title = "null";
            }
            Log.e("LNW","title "+ title);
            String data = (String) sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TEXT);
            if(data == null) {
                data = "null";
            }
            Log.e("LNW","TEXT "+ data);
            String peo = (String) sbn.getNotification().extras.getCharSequence(Notification. EXTRA_PEOPLE_LIST);
            if(peo == null) {
                peo = "null";
            }
            Log.e("LNW","PEO "+ peo);
            String sub = (String) sbn.getNotification().extras.getCharSequence(Notification. EXTRA_SUB_TEXT );
            if(sub == null) {
                sub = "null";
            }
            Log.e("LNW","SUB "+ sub);

        }
    }


}
