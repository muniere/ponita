package net.muniere.ponita.api.controller

import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import net.muniere.ponita.service.MessageService

public final class RootController(
    private val messages: MessageService
) {

    public suspend fun index(call: ApplicationCall) {
        call.respond(this.messages.take(10))
    }
}
