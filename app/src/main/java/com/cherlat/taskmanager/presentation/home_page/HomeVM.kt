package com.cherlat.taskmanager.presentation.home_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherlat.taskmanager.data.TasksData
import com.cherlat.taskmanager.data.fakeTaskData
import com.cherlat.taskmanager.model.TaskInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(getInitialUiState())
    val uiState = _uiState.asStateFlow()

    private fun getInitialUiState(): HomeUiState {
        return HomeUiState(
            tasks = fakeTaskData.tasks,
            completed = fakeTaskData.completedTasks
        )
    }

    fun markTaskAsDone(task: TaskInfo) {
        viewModelScope.launch {

            val state = _uiState.value
            val tasks = state.tasks.toMutableList().apply {
                this.removeIf { it.id == task.id }
            }
            val completed = state.completed.toMutableList().apply {
                this.add(task)
            }

            _uiState.value = HomeUiState(
                tasks = tasks,
                completed = completed
            )

        }
    }

    fun markTaskAsUndone(task: TaskInfo) {
        viewModelScope.launch {

            val state = _uiState.value

            val tasks = state.tasks.toMutableList().apply {
                this.add(task)
            }

            val completed = state.tasks.toMutableList().apply {
                this.removeIf { it.id == task.id }
            }

            _uiState.value = HomeUiState(
                tasks = tasks,
                completed = completed
            )

        }
    }

}

data class HomeUiState(
    val tasks: List<TaskInfo>,
    val completed: List<TaskInfo>
)