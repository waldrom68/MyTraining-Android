package com.rome.tech.mytraining.todo_app.viewHolder

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
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
                // El contexto que debo usar es el del divider que está en el layout
                // this no lo tiene.
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        divider.context,
                        R.color.todo_business_category
                    )
                )
            }

            TaskCategory.Other -> {
                tvCategoryName.text = "Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        divider.context,
                        R.color.todo_other_category
                    )
                )
            }

            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        divider.context,
                        R.color.todo_personal_category
                    )
                )
            }

            TaskCategory.Domestic -> {
                tvCategoryName.text = "Familiares"
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        divider.context,
                        R.color.todo_domestic_category
                    )
                )
            }
        }

    }
}