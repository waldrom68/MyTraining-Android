package com.rome.tech.mytraining.todo_app.viewHolder

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rome.tech.mytraining.R
import com.rome.tech.mytraining.todo_app.model.TaskCategory


class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private var divider: View = view.findViewById(R.id.divider)
    private var cardCategory: CardView = view.findViewById(R.id.cardCategory)

    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {

        // con el LayoutPosition se donde estoy
        itemView.setOnClickListener { onItemSelected(layoutPosition) }

        val cardColor = if (taskCategory.isSelected) {
            R.color.todo_background_card
        } else {
            R.color.todo_background_card_disable
        }
        cardCategory.setCardBackgroundColor(ContextCompat.getColor(itemView.context, cardColor))


        // TODO refactoring HardCode referenciado a res/string
        val categoryName: String
        val categoryColor: Int
        when (taskCategory) {
            TaskCategory.Business -> {
                categoryName = "Negocios"
                categoryColor = R.color.todo_business_category
            }

            TaskCategory.Personal -> {
                categoryName = "Personal"
                categoryColor = R.color.todo_personal_category
            }

            TaskCategory.Domestic -> {
                categoryName = "Familiares"
                categoryColor = R.color.todo_domestic_category
            }

            else -> {
                categoryName = "Otros"
                categoryColor = R.color.todo_other_category
            }
        }

        tvCategoryName.text = categoryName
        // El contexto que debo usar es el del divider que está en el layout
        // this no lo tiene.
        divider.setBackgroundColor(
            ContextCompat.getColor(divider.context, categoryColor)
        )

    }

}