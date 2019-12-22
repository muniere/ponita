package net.muniere.ponita.service

import net.muniere.ponita.domain.Message

public interface MessageService {
    public fun latest(): Message?
    public fun take(size: Int): List<Message>
    public fun create(content: String): Message?
    public fun delete(id: Int): Int
}
