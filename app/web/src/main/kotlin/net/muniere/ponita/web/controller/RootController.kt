package net.muniere.ponita.web.controller

import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import net.muniere.ponita.service.MessageService

public final class RootController(
    private val messages: MessageService
) {

    public suspend fun index(call: ApplicationCall) {
        when (val message = this.messages.latest()) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respondText(message.content)
        }
    }
}
