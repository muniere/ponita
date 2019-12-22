package net.muniere.ponita.web.dependency

import io.ktor.application.Application
import io.ktor.application.log
import net.muniere.ponita.service.MessageService
import net.muniere.ponita.service.MessageServiceDefault
import net.muniere.ponita.storage.DatabaseConfig
import net.muniere.ponita.storage.DatabaseManager
import net.muniere.ponita.storage.DatabaseManagerDefault
import net.muniere.ponita.storage.repository.MessageRepository
import net.muniere.ponita.storage.repository.MessageRepositoryDefault
import net.muniere.ponita.web.controller.RootController
import org.koin.dsl.module
import org.koin.experimental.builder.single
import org.koin.experimental.builder.singleBy
import org.slf4j.Logger
import java.io.File

public final class DependencyGraph {

    public companion object {
        public fun build(app: Application) = module {
            val database = System.getenv("DATABASE_FILE")?.takeIf { it.isNotEmpty() }?.let(::File) ?: run {
                createTempFile(suffix = ".sqlite")
            }

            factory<Logger> { app.log }
            factory<DatabaseConfig> { DatabaseConfig.SQLite(database.absolutePath) }
            singleBy<DatabaseManager, DatabaseManagerDefault>()
            singleBy<MessageRepository, MessageRepositoryDefault>()
            singleBy<MessageService, MessageServiceDefault>()
            single<RootController>()
        }
    }
}
