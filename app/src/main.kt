package net.muniere.ponita

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.netty.EngineMain
import net.muniere.ponita.lib.Message

fun main(args: Array<String>): Unit {
    EngineMain.main(args)
}

@Suppress("UNUSED_PARAMETER")
@kotlin.jvm.JvmOverloads
fun Application.launch(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson()
    }
    install(CallLogging)
}

@Suppress("UNUSED_PARAMETER")
@kotlin.jvm.JvmOverloads
fun Application.api(testing: Boolean = false) {
    routing {
        get("/api") {
            this.call.respond(Message("Hello, World"))
        }
    }
}

@Suppress("UNUSED_PARAMETER")
@kotlin.jvm.JvmOverloads
fun Application.web(testing: Boolean = false) {
    routing {
        get("/") {
            this.call.respondText("Hello, World")
        }
    }
}
