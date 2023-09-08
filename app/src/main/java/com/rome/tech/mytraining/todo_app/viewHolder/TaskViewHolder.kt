package com.rome.tech.mytraining.todo_app.viewHolder

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rome.tech.mytraining.R
import com.rome.tech.mytraining.todo_app.model.Task

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTask: TextView = view.findViewById(R.id.tvTaskName)
    private val chkTask: CheckBox = view.findViewById(R.id.chkTask)

    fun render(task: Task) {
        tvTask.text = task.name



    }

}