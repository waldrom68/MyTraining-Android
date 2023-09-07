package com.rome.tech.mytraining.todo_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rome.tech.mytraining.R
import com.rome.tech.mytraining.todo_app.adapter.CategoriesAdapter
import com.rome.tech.mytraining.todo_app.adapter.TaskAdapter
import com.rome.tech.mytraining.todo_app.model.Task
import com.rome.tech.mytraining.todo_app.model.TaskCategory


class TodoMainActivity : AppCompatActivity() {
    //    TODO esta info es la que se debe recibir para armar la lista
//    por ahora es fija
    private val categories = listOf(
        TaskCategory.Business,
        TaskCategory.Personal,
        TaskCategory.Domestic,
        TaskCategory.Other
    )

    private val tasks = mutableListOf<Task>(
        Task("Preparar mates", TaskCategory.Domestic, false)
    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var rvTasks: RecyclerView
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_main)

        initComponents()
        initUI()

    }

    private fun initComponents() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)

    }

    private fun initUI() {

        // Listado de categorias de Tareas
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        // Listado de tareas
        taskAdapter = TaskAdapter(tasks)
        rvTasks.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTasks.adapter = taskAdapter

    }

}