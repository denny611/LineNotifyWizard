package com.daniel.linenotifywizard.domain

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.daniel.linenotifywizard.data.MessageRepository
import com.daniel.linenotifywizard.data.MessageRepositoryImpl.Companion.getInstance
import com.daniel.linenotifywizard.model.NotifyMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationListener : NotificationListenerService() {
    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.e("LNW", "onListenerConnected")
    }

    /**
     * Implement this method to learn about new notifications as they are posted by apps.
     *
     * @param sbn A data structure encapsulating the original [Notification]
     * object as well as its identifying information (tag and id) and source
     * (package name).
     */
    override fun onNotificationPosted(sbn: StatusBarNotification) {
       // cancelNotification(sbn.key)
        super.onNotificationPosted(sbn)
        if (sbn.packageName.compareTo("jp.naver.line.android") == 0) {
        //if (sbn.packageName.compareTo("com.google.android.apps.messaging") != 0) {
            Log.e("LNW", sbn.toString())
            val key = sbn.key
            var title = sbn.notification.extras.getCharSequence(Notification.EXTRA_TITLE) as String?
            if (title == null) {
                title = "null"
            }
            Log.e("LNW", "title $title")
            var data = sbn.notification.extras.getCharSequence(Notification.EXTRA_TEXT) as String?
            if (data == null) {
                data = "null"
            }
            Log.e("LNW", "TEXT $data")
            var peo =
                sbn.notification.extras.getCharSequence(Notification.EXTRA_PEOPLE_LIST) as String?
            if (peo == null) {
                peo = "null"
            }
            Log.e("LNW", "PEO $peo")
            var sub =
                sbn.notification.extras.getCharSequence(Notification.EXTRA_SUB_TEXT) as String?
            if (sub == null) {
                sub = "null"
            }
            Log.e("LNW", "SUB $sub")
            val messageRepository: MessageRepository = getInstance()
            CoroutineScope(Dispatchers.IO).launch {
                messageRepository.addNotify(NotifyMessage(key, title, data))
            }
        }
    }
}
