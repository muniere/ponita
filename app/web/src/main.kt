package net.muniere.ponita.web

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>): Unit {
    EngineMain.main(args)
}

@Suppress("UNUSED_PARAMETER")
@kotlin.jvm.JvmOverloads
fun Application.launch(testing: Boolean = false) {
    install(CallLogging)
}

@Suppress("UNUSED_PARAMETER")
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        get("/") {
            this.call.respondText("Hello, World")
        }
    }
}

