package app.processors

import app.state.State
import app.state.state
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.bot.getMe
import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.TextContent

class StartProcessor(
    private val bot: TelegramBot
) : CommandProcessor {
    override val commandName: String = "start"

    override suspend fun onCommand(message: CommonMessage<TextContent>) {
        if (state != State.OPEN) return
        bot.reply(message, "Hello, I am ${bot.getMe().firstName}")
    }
}