package com.daniel.linenotifywizard.data

import com.daniel.linenotifywizard.model.NotifyMessage

interface MessageRepository {
    fun getAllNotify(): List<NotifyMessage>
    fun addNotify(notify:NotifyMessage)
}


class MessageRepositoryImpl: MessageRepository {
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
    override fun getAllNotify(): List<NotifyMessage> {
        return notifyList;
    }

    override fun addNotify(notify: NotifyMessage) {
        notifyList.add(notify)
    }

}