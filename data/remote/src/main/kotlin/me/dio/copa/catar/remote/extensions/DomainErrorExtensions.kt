package me.dio.copa.catar.remote.extensions

import me.dio.copa.catar.remote.NotFoundException
import me.dio.copa.catar.remote.UnexpectedException
import java.net.HttpURLConnection
import retrofit2.HttpException as RetrofitHttpException

internal fun <T> Result<T>.getOrThrowDomainError(): T = getOrElse { throwable ->
    throw throwable.toDomainError()
}

internal fun Throwable.toDomainError(): Throwable {
    return when (this) {
        is RetrofitHttpException -> {
            when (code()) {
                HttpURLConnection.HTTP_NOT_FOUND ->
                    NotFoundException("Ops! Não conseguimos encontrar as partidas :'(")
                else -> UnexpectedException()
            }
        }
        else -> this
    }
}
