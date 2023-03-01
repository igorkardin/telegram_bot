package app

import app.processors.CalculateProcessor
import app.processors.PokaProcessor
import app.processors.PrivetProcessor
import app.processors.StartProcessor
import dev.inmo.tgbotapi.bot.ktor.telegramBot
import dev.inmo.tgbotapi.extensions.api.bot.getMe
import dev.inmo.tgbotapi.extensions.behaviour_builder.buildBehaviourWithLongPolling
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onCommand
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onContentMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

suspend fun main() {
    val config = Config()
    val bot = telegramBot(config.token)

    val scope = CoroutineScope(Dispatchers.Default)

    bot.buildBehaviourWithLongPolling(scope) {
        val me = getMe()

        // Во первых залить на гит мастер веткой
        // Нижние 2 задания сделать в отдельной ветке task 2 и результат оформить пулл реквестом
        // Во вторых хочу чтобы из цифры 2 calculate вычитал 2, а остальным как обычно
        // В третьих, хочу чтобы обрабатывось сообщение "Poka" и отвечало "Proshai huesos"

        val start = StartProcessor(bot)
        val calculator = CalculateProcessor(bot)
        val privet = PrivetProcessor(bot)
        val poka = PokaProcessor(bot)

        val commandProcessors = listOf(start, calculator)
        commandProcessors.forEach { processor ->
            onCommand(processor.commandName, requireOnlyCommandInMessage = true) { processor.onCommand(it) }
        }

        val messageProcessor = listOf(privet, calculator, poka)
        messageProcessor.forEach { processor -> onContentMessage { processor.onMessage(it) } }

        println(me)
    }.join()
}