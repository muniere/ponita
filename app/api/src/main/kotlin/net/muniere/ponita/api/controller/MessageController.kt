package net.muniere.ponita.api.controller

import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import net.muniere.ponita.service.MessageService

public final class MessageController(
    private val messages: MessageService
) {

    //
    // Index
    //
    private data class IndexParams(
        public val size: Int,
        public val cursor: String? = null
    ) {
        companion object {
            public fun from(call: ApplicationCall) = IndexParams(
                size = call.request.queryParameters["size"]?.toIntOrNull() ?: 20,
                cursor = call.request.queryParameters["cursor"]
            )
        }
    }

    public suspend fun index(call: ApplicationCall) {
        val params = IndexParams.from(call)
        val messages = this.messages.take(params.size)

        call.respond(messages)
    }

    //
    // Create
    //
    private data class CreateBody(
        public val content: String
    )

    public suspend fun create(call: ApplicationCall) {
        val body = call.receive<CreateBody>()

        when (val result = this.messages.create(body.content)) {
            null -> call.respond(HttpStatusCode.BadRequest)
            else -> call.respond(result)
        }
    }

    //
    // Update
    //
    private data class UpdateParams(
        public val id: Int
    ) {
        companion object {
            public fun from(call: ApplicationCall) = UpdateParams(
                id = call.parameters.get("id")?.toInt().let(::requireNotNull)
            )
        }
    }

    private data class UpdateBody(
        public val content: String
    )

    public suspend fun update(call: ApplicationCall) {
        val params = UpdateParams.from(call)
        val body = call.receive<UpdateBody>()

        when (val result = this.messages.update(params.id, body.content)) {
            null -> call.respond(HttpStatusCode.BadRequest)
            else -> call.respond(result)
        }
    }

    //
    // Delete
    //
    private data class DeleteParams(
        public val id: Int
    ) {
        companion object {
            public fun from(call: ApplicationCall) = DeleteParams(
                id = call.parameters.get("id")?.toInt().let(::requireNotNull)
            )
        }
    }

    public suspend fun delete(call: ApplicationCall) {
        val params = DeleteParams.from(call)

        when (this.messages.delete(params.id)) {
            0 -> call.respond(HttpStatusCode.BadRequest)
            else -> call.respond(HttpStatusCode.NoContent)
        }
    }
}
