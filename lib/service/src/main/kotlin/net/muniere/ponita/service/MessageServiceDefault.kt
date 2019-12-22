package net.muniere.ponita.service

import net.muniere.ponita.domain.Message
import net.muniere.ponita.storage.repository.MessageRepository
import net.muniere.ponita.storage.table.Messages

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
        return this.repository.create(content)
    }

    override fun delete(id: Int): Int {
        return this.repository.delete(id)
    }
}
