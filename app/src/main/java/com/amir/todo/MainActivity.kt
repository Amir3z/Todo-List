package com.amir.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amir.todo.data.Todo
import com.amir.todo.ui.add_edit_todo.AddEditTodoScreen
import com.amir.todo.ui.theme.TodoTheme
import com.amir.todo.ui.todo_list.TodoListScreen
import com.amir.todo.util.Routes
import com.amir.todo.util.TODO_ID
import dagger.hilt.android.AndroidEntryPoint
import java.security.KeyStore

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.TODO_LIST_ROUTE
                ) {
                    composable(route = Routes.TODO_LIST_ROUTE) {
                        TodoListScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                    composable(
                        route = Routes.ADD_EDIT_TODO_ROUTE + "?$TODO_ID={$TODO_ID}",
                        arguments = listOf(
                            navArgument(name = TODO_ID) {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        AddEditTodoScreen(
                            onPopBackStack = { navController.popBackStack() }
                        )
                    }
                }

            }
        }
    }
}