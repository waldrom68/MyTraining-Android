package com.rome.tech.mytraining.todo_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rome.tech.mytraining.R
import com.rome.tech.mytraining.todo_app.adapter.CategoriesAdapter
import com.rome.tech.mytraining.todo_app.model.TaskCategory


class TodoMainActivity : AppCompatActivity() {
    private val categories = listOf(
        TaskCategory.Personal,
        TaskCategory.Business,
        TaskCategory.Other

    )
    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_main)

        initComponents()
        initUI()

    }

    private fun initComponents() {
        rvCategories = findViewById(R.id.rvCategories)

    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

    }

}