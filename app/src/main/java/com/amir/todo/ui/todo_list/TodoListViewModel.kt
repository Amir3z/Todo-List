package com.amir.todo.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amir.todo.data.Todo
import com.amir.todo.data.TodoRepository
import com.amir.todo.util.Routes
import com.amir.todo.util.TODO_ID
import com.amir.todo.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    val todos = repository.getAllTodos()

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent get() = _uiEvent.receiveAsFlow()

    private var deletedTodo: Todo? = null

    fun onEvent(event: TodoListEvent) {
        when (event) {
            is TodoListEvent.OnDeleteTodo -> {
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deleteTodo(event.todo)
                    sendUiEvent(
                        UIEvent.ShowSnackBar(
                            "Todo deleted",
                            "Undo"
                        )
                    )
                }
            }

            is TodoListEvent.OnAddTodoClick -> {
                sendUiEvent(UIEvent.Navigate(Routes.ADD_EDIT_TODO_ROUTE))
            }

            is TodoListEvent.OnTodoClicked -> {
                sendUiEvent(
                    UIEvent.Navigate(
                        Routes.ADD_EDIT_TODO_ROUTE + "?$TODO_ID=${event.todo.id}"
                    )
                )
            }

            is TodoListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insertTodo(event.todo.copy(isDone = event.isDone))
                }
            }

            is TodoListEvent.OnUndoDelete -> {
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }
            }
        }
    }

    private fun sendUiEvent(event: UIEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}