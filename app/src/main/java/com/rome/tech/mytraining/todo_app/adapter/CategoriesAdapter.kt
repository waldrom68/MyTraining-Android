package com.rome.tech.mytraining.todo_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rome.tech.mytraining.R
import com.rome.tech.mytraining.todo_app.viewHolder.CategoriesViewHolder
import com.rome.tech.mytraining.todo_app.model.TaskCategory


// Encargado de armer y mostrar la lista
class CategoriesAdapter(private val categories: List<TaskCategory>): RecyclerView.Adapter<
        CategoriesViewHolder>
    () {

    // Es el que toma la vista xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent,false)
        return CategoriesViewHolder(view)
    }

    // Es el que determina el largo de la lista
    override fun getItemCount() = categories.size

    // Es el que le agrega los datos
    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        return holder.render(categories[position])
    }

}