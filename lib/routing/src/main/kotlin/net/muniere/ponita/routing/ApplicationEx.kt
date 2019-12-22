package net.muniere.ponita.routing

import io.ktor.application.Application
import io.ktor.routing.Routing
import io.ktor.routing.routing

public fun Application.associate(configuration: Association.() -> Unit): Routing = routing {
    configuration.invoke(Association(this))
}
