package net.muniere.ponita.routing

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.routing.Route
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.put

public final class Association(
    private val base: Route
) {

    public fun get(path: String, handler: suspend (ApplicationCall) -> Unit): Route {
        return this.base.get(path) { handler.invoke(call) }
    }

    public fun post(path: String, handler: suspend (ApplicationCall) -> Unit): Route {
        return this.base.post(path) { handler.invoke(call) }
    }

    public fun put(path: String, handler: suspend (ApplicationCall) -> Unit): Route {
        return this.base.put(path) { handler.invoke(call) }
    }

    public fun delete(path: String, handler: suspend (ApplicationCall) -> Unit): Route {
        return this.base.delete(path) { handler.invoke(call) }
    }
}
