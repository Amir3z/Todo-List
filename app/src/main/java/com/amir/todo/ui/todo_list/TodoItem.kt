package com.amir.todo.ui.todo_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amir.todo.data.Todo

@Composable
fun TodoItem(
    todo: Todo,
    modifier: Modifier = Modifier,
    onEvent: (TodoListEvent) -> Unit
) {

    Card(
        elevation = 4.dp,
        border = BorderStroke(2.dp, MaterialTheme.colors.secondaryVariant),
        modifier = modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = todo.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(onClick = { onEvent(TodoListEvent.OnDeleteTodo(todo)) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete"
                        )
                    }
                }
                todo.description?.let { desc ->
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = desc)
                }
            }
            Checkbox(checked = todo.isDone, onCheckedChange = { isChecked ->
                onEvent(TodoListEvent.OnDoneChange(todo, isChecked))
            })
        }
    }



}