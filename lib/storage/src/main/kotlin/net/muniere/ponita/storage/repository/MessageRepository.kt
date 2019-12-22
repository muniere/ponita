package net.muniere.ponita.storage.repository

import net.muniere.ponita.domain.Message
import net.muniere.ponita.storage.table.Messages
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.joda.time.DateTime

public interface MessageRepository {
    public fun find(id: Int): Message?
    public fun take(size: Int): List<Message>
    public fun search(content: String): List<Message>
    public fun insert(content: String): Int
    public fun create(content: String): Message?
    public fun update(id: Int, content: String): Int
    public fun delete(id: Int): Int
}
