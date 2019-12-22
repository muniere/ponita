package net.muniere.ponita.storage.repository

import net.muniere.ponita.domain.Message

public interface MessageRepository {
    public fun find(id: Int): Message?
    public fun take(size: Int): List<Message>
    public fun search(content: String): List<Message>
    public fun insert(content: String): Int
    public fun insertAndGet(content: String): Message?
    public fun update(id: Int, content: String): Int
    public fun updateAndGet(id: Int, content: String): Message?
    public fun delete(id: Int): Int
}
