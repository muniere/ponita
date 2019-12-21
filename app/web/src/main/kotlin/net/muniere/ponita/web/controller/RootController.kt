package net.muniere.ponita.web.controller

import io.ktor.application.ApplicationCall
import io.ktor.response.respond
import io.ktor.response.respondText
import net.muniere.ponita.service.MessageService

public final class RootController(
    private val messages: MessageService
) {

    public suspend fun index(call: ApplicationCall) {
        call.respondText(this.messages.get().text)
    }
}
