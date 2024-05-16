package com.daniel.linenotifywizard.domain

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.daniel.linenotifywizard.data.MessageRepository
import com.daniel.linenotifywizard.data.MessageRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotifyMangerService : Service(
    //private val selectedNote: Observable<Optional<StickyNote>>
){
    private lateinit var noteRepository: MessageRepository
    companion object {
        @Volatile
        private lateinit var instance: NotifyMangerService
        fun getInstance(): NotifyMangerService {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = NotifyMangerService()
                }
                return instance
            }
        }
    }
    suspend fun updateNotify() {
        Log.e("LNW", "updateNotify")
        val events = noteRepository.getAllNotify()
        events.collect{value -> println("Collected LNW $value")
        }
        //if(l.isNotEmpty())
         //   Log.e("LNW-M", l[l.size - 1].toString())

    }
    init {
        noteRepository = MessageRepositoryImpl.getInstance()
    }

    /**
     * Return the communication channel to the service.  May return null if
     * clients can not bind to the service.  The returned
     * [android.os.IBinder] is usually for a complex interface
     * that has been [described using
 * aidl]({@docRoot}guide/components/aidl.html).
     *
     *
     * *Note that unlike other application components, calls on to the
     * IBinder interface returned here may not happen on the main thread
     * of the process*.  More information about the main thread can be found in
     * [Processes and
 * Threads]({@docRoot}guide/topics/fundamentals/processes-and-threads.html).
     *
     * @param intent The Intent that was used to bind to this service,
     * as given to [ Context.bindService][android.content.Context.bindService].  Note that any extras that were included with
     * the Intent at that point will *not* be seen here.
     *
     * @return Return an IBinder through which clients can call on to the
     * service.
     */
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    /**
     * Called by the system every time a client explicitly starts the service by calling
     * [android.content.Context.startService], providing the arguments it supplied and a
     * unique integer token representing the start request.  Do not call this method directly.
     *
     *
     * For backwards compatibility, the default implementation calls
     * [.onStart] and returns either [.START_STICKY]
     * or [.START_STICKY_COMPATIBILITY].
     *
     *
     * Note that the system calls this on your
     * service's main thread.  A service's main thread is the same
     * thread where UI operations take place for Activities running in the
     * same process.  You should always avoid stalling the main
     * thread's event loop.  When doing long-running operations,
     * network calls, or heavy disk I/O, you should kick off a new
     * thread, or use [android.os.AsyncTask].
     *
     * @param intent The Intent supplied to [android.content.Context.startService],
     * as given.  This may be null if the service is being restarted after
     * its process has gone away, and it had previously returned anything
     * except [.START_STICKY_COMPATIBILITY].
     * @param flags Additional data about this start request.
     * @param startId A unique integer representing this specific request to
     * start.  Use with [.stopSelfResult].
     *
     * @return The return value indicates what semantics the system should
     * use for the service's current started state.  It may be one of the
     * constants associated with the [.START_CONTINUATION_MASK] bits.
     *
     * @see .stopSelfResult
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("LNWNotifyMangerService","onStartCommand");
        CoroutineScope(Dispatchers.IO).launch  {
            updateNotify()
        }
        return super.onStartCommand(intent, flags, startId)
    }
}