package com.dicoding.bangkitmerchstore.ui.common

sealed class StateHolder<out T: Any?> {
    object Loading : StateHolder<Nothing>()
    data class Success<out T: Any>(val data: T) : StateHolder<T>()
    data class Error(val errorMessage: String) : StateHolder<Nothing>()
}