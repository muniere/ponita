package net.muniere.ponita.service

import net.muniere.ponita.domain.Message

public final class MessageServiceDefault : MessageService {

    public override fun get(): Message {
        return Message("Hello, World")
    }
}
