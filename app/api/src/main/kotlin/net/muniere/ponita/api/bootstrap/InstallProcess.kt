package net.muniere.ponita.api.bootstrap

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import net.muniere.ponita.api.dependency.DependencyGraph
import net.muniere.ponita.storage.DatabaseManager
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject

public final class InstallProcess : Function1<Application, Unit> {

    override fun invoke(app: Application) {
        app.install(Koin) {
            modules(DependencyGraph.build(app))
        }
        app.install(ContentNegotiation) {
            gson()
        }
        app.install(CallLogging)

        app.inject<DatabaseManager>().value.bootstrap()
    }
}
