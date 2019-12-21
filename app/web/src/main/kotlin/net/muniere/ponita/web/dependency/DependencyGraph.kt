package net.muniere.ponita.web.dependency

import net.muniere.ponita.service.MessageService
import net.muniere.ponita.service.MessageServiceDefault
import net.muniere.ponita.web.controller.RootController
import org.koin.dsl.module
import org.koin.experimental.builder.single
import org.koin.experimental.builder.singleBy

public final class DependencyGraph {

    public companion object {
        public fun build() = module {
            single<RootController>()
            singleBy<MessageService, MessageServiceDefault>()
        }
    }
}
