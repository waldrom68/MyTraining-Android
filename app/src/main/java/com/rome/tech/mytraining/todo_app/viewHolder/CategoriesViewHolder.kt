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

        // TODO refactoring HardCode referenciado a res/string
        val categoryName: String
        val categoryColorId: Int
        when (taskCategory) {
            TaskCategory.Business -> {
                categoryName = "Negocios"
                categoryColorId = ContextCompat.getColor(
                    divider.context,
                    R.color.todo_business_category
                )
            }

            TaskCategory.Personal -> {
                categoryName = "Personal"
                categoryColorId = ContextCompat.getColor(
                    divider.context,
                    R.color.todo_personal_category
                )
            }

            TaskCategory.Domestic -> {
                categoryName = "Familiares"
                categoryColorId = ContextCompat.getColor(
                    divider.context,
                    R.color.todo_domestic_category
                )
            }

            else -> {
                categoryName = "Otros"
                categoryColorId = ContextCompat.getColor(
                    divider.context,
                    R.color.todo_other_category
                )
            }
        }

        tvCategoryName.text = categoryName
        // El contexto que debo usar es el del divider que está en el layout
        // this no lo tiene.
        divider.setBackgroundColor(categoryColorId)

    }

}