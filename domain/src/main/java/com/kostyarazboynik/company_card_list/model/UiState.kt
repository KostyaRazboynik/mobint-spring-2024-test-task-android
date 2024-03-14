package com.kostyarazboynik.company_card_list.model

sealed class UiState<out T> {
    data object Initial : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val cause: String, val code: Int) : UiState<Nothing>()
}