package com.cherlat.taskmanager.model


data class TaskInfo(
    val id: Int,
    val text: String,
    val timeStamp: Long,
    val completed: Boolean
)

