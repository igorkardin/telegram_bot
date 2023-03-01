package app.processors

import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent

interface MessageProcessor {
    suspend fun onMessage(message: CommonMessage<MessageContent>)
}