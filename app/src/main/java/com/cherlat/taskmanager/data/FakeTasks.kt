package com.cherlat.taskmanager.data

import com.cherlat.taskmanager.model.TaskInfo


data class TasksData(
    val tasks: List<TaskInfo>,
    val completedTasks: List<TaskInfo>
)

val fakeTaskData = TasksData(
    tasks = listOf(
        TaskInfo(1, "Lock the car doors", System.currentTimeMillis(), false),
        TaskInfo(2, "Check that the front door is locked and all gadgets are charged", System.currentTimeMillis(), false),
        TaskInfo(3, "Check that the front door is locked and all gadgets are charged", System.currentTimeMillis(), false),
        TaskInfo(4, "Check that the front door is locked and all gadgets are charged", System.currentTimeMillis(), false),
    ),
    completedTasks = listOf(
        TaskInfo(5, "Lock the car doors", System.currentTimeMillis(), false),
        TaskInfo(6, "Check that the front door is locked and all gadgets are charged", System.currentTimeMillis(), false),
    )
)