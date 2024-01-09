package me.dio.copa.catar.notification.scheduler.extensions

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import me.dio.copa.catar.domain.model.MatchDomain
import java.time.Duration
import java.time.LocalDateTime

private const val NOTIFICATION_TITLE_KEY = "NOTIFICATION_TITLE_KEY"
private const val NOTIFICATION_CONTENT_KEY = "NOTIFICATION_CONTENT_KEY"

class NotificationMatcherWorker(
    //acessar a api de notificação do android precisa do contexto
    private val context: Context,
    workerParameters: WorkerParameters
) :
    Worker(context, workerParameters) {

    //receber título  e conteúdo da notificação
    override fun doWork(): Result {
        val title = inputData.getString("NOTIFICATION_TITLE_KEY")
            ?: throw IllegalArgumentException("title is required")
        val content = inputData.getString("NOTIFICATION_CONTENT_KEY")
            ?: throw IllegalArgumentException("content is required")

        //extensão criada no escopo de construtor
        context.showNotification(title, content)

        return Result.success()
    }
    //ser notificado, ou cancelar a notificação
    companion object {
        fun start(context: Context, match: MatchDomain) {
            //agendar -  acessas a instãncia do work

            val(id, _,_, team1, team2, matchDate) = match

            //declarando o delay
            val initialDelay = Duration.between(LocalDateTime.now(), matchDate).minusMinutes(5)
            val inputData = workDataOf(
                NOTIFICATION_TITLE_KEY to "Se prepare que o jogo vai começar!",
                NOTIFICATION_CONTENT_KEY to "Hoje tem ${team1.flag} x ${team2.flag} !",
            )

            //trabalhando com array:
            //val(dia, mes, ano) = "08-12-1992".split("-")

            WorkManager.getInstance(context)
                .enqueueUniqueWork(
                    id,
                    ExistingWorkPolicy.KEEP,
                    createRequest(initialDelay, inputData)
                )
        }

        fun cancel(context: Context, match: MatchDomain) {
            WorkManager.getInstance(context)
                .cancelUniqueWork(
                    match.id
                )
        }

        private fun createRequest(initialDelay: Duration,inputData: Data): OneTimeWorkRequest =
            //declarar quem vai ser executado
            OneTimeWorkRequestBuilder<NotificationMatcherWorker>()
                .setInitialDelay(initialDelay)
                .setInputData(inputData)
                .build() //design p. de builder - cria uma referência e passa os dados que serão executados
    }

}