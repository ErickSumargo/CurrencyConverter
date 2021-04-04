package com.bael.interview.domain.common.response

import java.io.Serializable

/**
 * Created by ErickSumargo on 01/04/21.
 */

sealed class Response<out T> : Serializable {

    object Loading : Response<Nothing>()

    data class Error(val error: Exception) : Response<Nothing>()

    object Empty : Response<Nothing>()

    data class Success<T>(val data: T) : Response<T>()

    operator fun invoke(): T? {
        return when (this) {
            is Success -> data
            else -> null
        }
    }
}
