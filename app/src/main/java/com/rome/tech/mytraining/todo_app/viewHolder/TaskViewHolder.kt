package com.rome.tech.mytraining.todo_app.viewHolder

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rome.tech.mytraining.R
import com.rome.tech.mytraining.todo_app.model.Task
import com.rome.tech.mytraining.todo_app.model.TaskCategory

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTask: TextView = view.findViewById(R.id.tvTaskName)
    private val chkTask: CheckBox = view.findViewById(R.id.chkTask)

    fun render(task: Task) {
        tvTask.text = task.name
        if (task._isSelected) {
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        chkTask.isChecked = task._isSelected

        // TODO refactoring Hardcoded rules
        val colorCategory = when (task.category) {
            TaskCategory.Business -> R.color.todo_business_category
            TaskCategory.Domestic -> R.color.todo_domestic_category
            TaskCategory.Personal -> R.color.todo_personal_category
            else -> R.color.todo_other_category
        }

        chkTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(chkTask.context, colorCategory)
        )

    }

}