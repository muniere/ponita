package net.muniere.ponita.service

import net.muniere.ponita.domain.Message
import net.muniere.ponita.storage.repository.MessageRepository

public final class MessageServiceDefault(
    private val repository: MessageRepository
) : MessageService {

    override fun latest(): Message? {
        return this.repository.take(1).firstOrNull()
    }

    override fun take(size: Int): List<Message> {
        return this.repository.take(size)
    }

    override fun create(content: String): Message? {
        return this.repository.insertAndGet(content)
    }

    override fun update(id: Int, content: String): Message? {
        return this.repository.updateAndGet(id, content)
    }

    override fun delete(id: Int): Int {
        return this.repository.delete(id)
    }
}
