package app.processors

import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.TextContent

interface CommandProcessor {
    val commandName: String
    suspend fun onCommand(message: CommonMessage<TextContent>)
}