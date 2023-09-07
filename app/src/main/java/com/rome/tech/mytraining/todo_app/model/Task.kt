package com.rome.tech.mytraining.todo_app.model

data class Task(val name: String,
                val category: TaskCategory,
                var _isSelected: Boolean = false
) {


}