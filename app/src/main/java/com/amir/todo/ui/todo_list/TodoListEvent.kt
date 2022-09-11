package com.amir.todo.ui.todo_list

import com.amir.todo.data.Todo

sealed class TodoListEvent {
    data class OnDeleteTodo(val todo: Todo): TodoListEvent()
    data class OnDoneChange(val todo: Todo, val isDone: Boolean): TodoListEvent()
    object OnUndoDelete: TodoListEvent()
    data class OnTodoClicked(val todo: Todo): TodoListEvent()
    object OnAddTodoClick: TodoListEvent()
}
