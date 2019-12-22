package net.muniere.ponita.domain

import java.util.*

public final data class Message(
    public val id: Int,
    public val content: String,
    public val createdAt: Date,
    public val updatedAt: Date
)
