package app.processors

import app.state.State
import app.state.state
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.send
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.text
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent

class PokaProcessor(
    private val bot: TelegramBot
) : MessageProcessor {
    override suspend fun onMessage(message: CommonMessage<MessageContent>) {
        if (state != State.OPEN || message.text != "Poka") return
        bot.send(message.chat, "Proshai huesos")
    }
}