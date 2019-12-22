package net.muniere.ponita.api

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.server.netty.EngineMain
import net.muniere.ponita.api.controller.MessageController
import net.muniere.ponita.api.controller.RootController
import net.muniere.ponita.api.dependency.DependencyGraph
import net.muniere.ponita.routing.associate
import net.muniere.ponita.storage.DatabaseManager
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject

fun main(args: Array<String>): Unit {
    EngineMain.main(args)
}

@Suppress("UNUSED_PARAMETER")
@kotlin.jvm.JvmOverloads
fun Application.launch(testing: Boolean = false) {
    install(Koin) {
        modules(DependencyGraph.build(this@launch))
    }
    install(ContentNegotiation) {
        gson()
    }
    install(CallLogging)

    inject<DatabaseManager>().value.bootstrap()
}

@Suppress("UNUSED_PARAMETER")
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val root: RootController by inject()
    val message: MessageController by inject()

    associate {
        get("/", root::index)
        get("/messages", message::index)
        post("/messages", message::create)
        put("/messages/{id}", message::update)
        delete("/messages/{id}", message::delete)
    }
}
