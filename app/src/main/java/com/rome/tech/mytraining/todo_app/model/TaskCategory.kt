package com.rome.tech.mytraining.todo_app.model

sealed class TaskCategory(var isSelected: Boolean) {

    data object Personal : TaskCategory(true)
    data object Business : TaskCategory(true)
    data object Other : TaskCategory(true)
    data object Domestic : TaskCategory(true)

}

/* Forma de agregar atributos a un objeto específico de una clase
sealed class TaskCategoryOtro {

    data class Personal (var isSelected: Boolean) : TaskCategoryOtro()
    object Business : TaskCategoryOtro()
    object Other : TaskCategoryOtro()
    object Domestic : TaskCategoryOtro()
}

*/
