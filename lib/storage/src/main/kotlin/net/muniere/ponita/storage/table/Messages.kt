package net.muniere.ponita.storage.table

import org.jetbrains.exposed.dao.IntIdTable

public object Messages: IntIdTable("messages") {
    public val content = varchar("content", 65535)
    public val createdAt = datetime("created_at")
    public val updatedAt = datetime("updated_at")
    public val deletedAt = datetime("deleted_at").nullable()
}

