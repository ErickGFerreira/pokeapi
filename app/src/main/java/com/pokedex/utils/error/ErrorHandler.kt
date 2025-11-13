package com.pokedex.utils.error

import com.pokedex.utils.safe.SafeRunException
import java.io.InterruptedIOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorHandler {

    val default = Error(
        title = "Algo deu errado por aqui",
        message = "Já estamos agilizando tudo pra resolver. Aguarde alguns instantes e tente novamente."
    )

    val timeOutError = Error(
        title = "Sem internet no momento",
        message = "Confira se o Wi-Fi ou os dados móveis estão funcionando e tente novamente.",
    )

    val connectivityError = timeOutError

    fun convertError(error: Any): Error = when (error) {
        is SocketTimeoutException -> timeOutError
        is SafeRunException -> error.error
        is InterruptedIOException -> timeOutError
        is UnknownHostException -> connectivityError
        else -> default
    }
}