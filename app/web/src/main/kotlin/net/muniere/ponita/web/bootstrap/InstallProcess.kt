package net.muniere.ponita.web.bootstrap

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import net.muniere.ponita.storage.DatabaseManager
import net.muniere.ponita.web.dependency.DependencyGraph
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject

public final class InstallProcess : Function1<Application, Unit> {

    override fun invoke(app: Application) {
        app.install(Koin) {
            modules(DependencyGraph.build(app))
        }

        app.install(CallLogging)

        app.inject<DatabaseManager>().value.bootstrap()
    }
}
