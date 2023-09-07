package com.rome.tech.mytraining.todo_app.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rome.tech.mytraining.R
import com.rome.tech.mytraining.todo_app.model.TaskCategory


class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private var divider: View = view.findViewById(R.id.divider)

    fun render(taskCategory: TaskCategory) {
        tvCategoryName.text = "EJEMPLO"

        when (taskCategory) {
            TaskCategory.Business -> {
                tvCategoryName.text = "Negocios"
            }
            TaskCategory.Other -> {
                tvCategoryName.text = "Otros"
            }
            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
            }
        }

    }
}