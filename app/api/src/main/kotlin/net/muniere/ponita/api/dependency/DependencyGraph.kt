package net.muniere.ponita.api.dependency

import net.muniere.ponita.api.controller.RootController
import net.muniere.ponita.service.MessageService
import net.muniere.ponita.service.MessageServiceDefault
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
