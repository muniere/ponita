package net.muniere.ponita.api.bootstrap

import io.ktor.application.Application
import net.muniere.ponita.api.controller.MessageController
import net.muniere.ponita.api.controller.RootController
import net.muniere.ponita.routing.associate
import org.koin.ktor.ext.inject

public final class RoutingProcess : Function1<Application, Unit> {

    override fun invoke(app: Application) {
        val root: RootController by app.inject()
        val message: MessageController by app.inject()

        app.associate {
            get("/", root::index)
            get("/messages", message::index)
            post("/messages", message::create)
            put("/messages/{id}", message::update)
            delete("/messages/{id}", message::delete)
        }
    }
}
