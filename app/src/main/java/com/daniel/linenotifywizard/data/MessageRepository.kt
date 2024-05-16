package com.daniel.linenotifywizard.data

import com.daniel.linenotifywizard.model.NotifyMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface MessageRepository {
    fun getAllNotify(): Flow<NotifyMessage>
    suspend fun addNotify(notify:NotifyMessage)
}


class MessageRepositoryImpl: MessageRepository {
    val events = MutableSharedFlow<NotifyMessage>()
    companion object {
        @Volatile
        private lateinit var instance: MessageRepositoryImpl
        fun getInstance(): MessageRepositoryImpl{
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = MessageRepositoryImpl()
                }
                return instance
            }
        }
    }


    private val notifyList: MutableList <NotifyMessage> = mutableListOf()
    override fun getAllNotify(): Flow<NotifyMessage> {
        return events
    }

    override suspend fun addNotify(notify: NotifyMessage) {
        events.emit(notify)
    }

}