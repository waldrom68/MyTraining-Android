package com.rome.tech.mytraining.todo_app.model

sealed class TaskCategory {

    object Personal: TaskCategory()
    object Business: TaskCategory()
    object Other: TaskCategory()
    object Domestic: TaskCategory()

}