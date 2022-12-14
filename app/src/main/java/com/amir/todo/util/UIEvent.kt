package com.amir.todo.util

sealed class UIEvent {

    object PopBackStack: UIEvent()
    data class Navigate(val route: String): UIEvent()
    data class ShowSnackBar(
        val message: String,
        val action: String? = null
    ):UIEvent()

}
