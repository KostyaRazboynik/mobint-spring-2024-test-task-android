package com.kostyarazboynik.company_card_list.model

sealed class DataState<out T> {
    data object Initial : DataState<Nothing>()
    data class Result<T>(val data: T) : DataState<T>()
    data class Exception(val cause: Throwable, val code: Int) : DataState<Nothing>()
}
