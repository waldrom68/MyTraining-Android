package com.rome.tech.mytraining.todo_app.viewHolder

import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rome.tech.mytraining.R
import com.rome.tech.mytraining.todo_app.model.TaskCategory
import kotlinx.coroutines.currentCoroutineContext


fun resolveThemeAttr(context: Context, @AttrRes attrRes: Int): TypedValue {
    val theme = context.theme
    val typedValue = TypedValue()
    theme.resolveAttribute(attrRes, typedValue, true)
    return typedValue
}
@ColorInt
fun resolveColorAttr(context: Context, @AttrRes colorAttr: Int): Int {
    val resolvedAttr = resolveThemeAttr(context, colorAttr)
    // resourceId is used if it's a ColorStateList, and data if it's a color reference or a hex color
    val colorRes = if (resolvedAttr.resourceId != 0)
        resolvedAttr.resourceId
    else
        resolvedAttr.data
    return ContextCompat.getColor(context, colorRes)
}


class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private var divider: View = view.findViewById(R.id.divider)
    private var cardCategory: CardView = view.findViewById(R.id.cardCategory)

    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {

        // con el LayoutPosition se donde estoy
        itemView.setOnClickListener { onItemSelected(layoutPosition) }


        val cardColor = if (taskCategory.isSelected) {
            R.color.todo_background_card_enable
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