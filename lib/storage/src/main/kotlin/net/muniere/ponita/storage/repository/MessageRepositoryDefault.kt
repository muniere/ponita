package net.muniere.ponita.storage.repository

import net.muniere.ponita.domain.Message
import net.muniere.ponita.storage.table.Messages
import net.muniere.ponita.storage.table.Messages.content
import net.muniere.ponita.storage.table.Messages.createdAt
import net.muniere.ponita.storage.table.Messages.id
import net.muniere.ponita.storage.table.Messages.updatedAt
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNull
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

public final class MessageRepositoryDefault : MessageRepository {

    override fun find(id: Int) = transaction {
        val predicate = { _: SqlExpressionBuilder ->
            Messages.id eq id and
                Messages.deletedAt.isNull()
        }

        return@transaction Messages
            .select(predicate)
            .singleOrNull()
            ?.let(::build)
    }

    override fun take(size: Int): List<Message> = transaction {
        val predicate = { _: SqlExpressionBuilder ->
            Messages.deletedAt.isNull()
        }

        return@transaction Messages
            .select(predicate)
            .orderBy(Messages.id, SortOrder.DESC)
            .limit(size)
            .map(::build)
    }

    override fun search(content: String) = transaction {
        val predicate = { _: SqlExpressionBuilder ->
            Messages.content like "%${content}%" and
                Messages.deletedAt.isNull()
        }

        return@transaction Messages
            .select(predicate)
            .map(::build)
    }

    override fun insert(content: String): Int = transaction {
        val now = DateTime()

        val id = Messages.insertAndGetId {
            it[Messages.content] = content
            it[Messages.createdAt] = now
            it[Messages.updatedAt] = now
        }

        return@transaction id.value
    }

    override fun insertAndGet(content: String): Message? = transaction {
        val now = DateTime()

        val id = Messages.insertAndGetId {
            it[Messages.content] = content
            it[Messages.createdAt] = now
            it[Messages.updatedAt] = now
        }

        return@transaction find(id.value)
    }

    override fun update(id: Int, content: String): Int = transaction {
        val now = DateTime()

        val affected = Messages.update({ Messages.id eq id }) {
            it[Messages.content] = content
            it[Messages.updatedAt] = now
        }

        return@transaction affected
    }

    override fun updateAndGet(id: Int, content: String): Message? = transaction {
        val now = DateTime()

        Messages.update({ Messages.id eq id }) {
            it[Messages.content] = content
            it[Messages.updatedAt] = now
        }

        return@transaction find(id)
    }

    override fun delete(id: Int): Int = transaction {
        val now = DateTime()

        val affected = Messages.update({ Messages.id eq id }) {
            it[Messages.deletedAt] = now
        }

        return@transaction affected
    }

    private fun build(row: ResultRow) = Message(
        id = row[id].value,
        content = row[content],
        createdAt = row[createdAt].toDate(),
        updatedAt = row[updatedAt].toDate()
    )
}
