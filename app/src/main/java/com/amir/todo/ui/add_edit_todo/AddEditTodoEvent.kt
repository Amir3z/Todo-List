package com.amir.todo.ui.add_edit_todo

sealed class AddEditTodoEvent {
    data class OnTitleChange(val title: String): AddEditTodoEvent()
    data class OnDescriptionChange(val description: String): AddEditTodoEvent()
    object OnSaveClick: AddEditTodoEvent()
}
