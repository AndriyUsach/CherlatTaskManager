package com.cherlat.taskmanager.presentation.home_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherlat.taskmanager.data.fakeTaskData
import com.cherlat.taskmanager.model.TaskInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(getInitialUiState())
    val uiState = _uiState.asStateFlow()

    private fun getInitialUiState(): HomeUiState {
        return HomeUiState(
            todo = fakeTaskData.tasks,
            completed = fakeTaskData.completedTasks,
            progress = 0.5f
        )
    }

    private fun calculateProgress(todo: Int, completed: Int): Float {
        return completed.toFloat() / (todo + completed).toFloat()
    }

    fun markTaskAsDone(task: TaskInfo) {
        viewModelScope.launch {

            val state = _uiState.value

            val todo = state.todo.toMutableList().apply {
                this.removeIf { it.id == task.id }
            }
            val completed = state.completed.toMutableList().apply {
                this.add(task)
            }

            _uiState.value = HomeUiState(
                todo = todo,
                completed = completed,
                progress = calculateProgress(todo.size, completed.size)
            )

        }
    }

    fun markTaskAsUndone(task: TaskInfo) {
        viewModelScope.launch {

            val state = _uiState.value

            val todo = state.todo.toMutableList().apply {
                this.add(task)
            }

            val completed = state.completed.toMutableList().apply {
                this.removeIf { it.id == task.id }
            }

            _uiState.value = HomeUiState(
                todo = todo,
                completed = completed,
                progress = calculateProgress(todo.size, completed.size)
            )

        }
    }

}

data class HomeUiState(
    val todo: List<TaskInfo>,
    val completed: List<TaskInfo>,
    val progress: Float,
)