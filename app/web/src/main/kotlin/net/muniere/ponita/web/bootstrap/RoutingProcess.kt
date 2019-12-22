package net.muniere.ponita.web.bootstrap

import io.ktor.application.Application
import net.muniere.ponita.routing.associate
import net.muniere.ponita.web.controller.RootController
import org.koin.ktor.ext.inject

public final class RoutingProcess : Function1<Application, Unit> {

    override fun invoke(app: Application) {
        val root: RootController by app.inject()

        app.associate {
            get("/", root::index)
        }
    }
}
