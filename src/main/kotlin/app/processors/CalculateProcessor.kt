package app.processors

import app.state.State
import app.state.state
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.text
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent
import dev.inmo.tgbotapi.types.message.content.TextContent

class CalculateProcessor(
    private val bot: TelegramBot
) : CommandProcessor, MessageProcessor {

    override val commandName: String = "calculate"

    override suspend fun onCommand(message: CommonMessage<TextContent>) {
        if (state == State.OPEN) {
            state = State.CALCULATOR
        } else {
            bot.reply(message, "Вы ввели хуйню команду")
            return
        }

        bot.reply(message, "Введи цифру")
    }

    override suspend fun onMessage(message: CommonMessage<MessageContent>) {
        if (state != State.CALCULATOR) return

        val text = message.text
        if (text == null) {
            bot.reply(message, "Вы ввели нул хуйню")
        } else {
            val number = try {
                text.toInt()
            } catch (e: NumberFormatException) {
                bot.reply(message, "Вы ввели хуйню а не цифру")
                return
            }

            val result: Int = if (number == 2) {
                0
            }else
                number + 2
            bot.reply(message, "Ответ $result")
            state = State.OPEN
        }
    }
}