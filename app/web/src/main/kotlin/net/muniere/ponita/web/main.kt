package net.muniere.ponita.web

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.netty.EngineMain
import net.muniere.ponita.web.controller.RootController
import net.muniere.ponita.web.dependency.DependencyGraph
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject

fun main(args: Array<String>): Unit {
    EngineMain.main(args)
}

@Suppress("UNUSED_PARAMETER")
@kotlin.jvm.JvmOverloads
fun Application.launch(testing: Boolean = false) {
    install(Koin) {
        modules(DependencyGraph.build())
    }
    install(CallLogging)
}

@Suppress("UNUSED_PARAMETER")
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val ctrl: RootController by inject()

    routing {
        get("/") {
            ctrl.index(call)
        }
    }
}

