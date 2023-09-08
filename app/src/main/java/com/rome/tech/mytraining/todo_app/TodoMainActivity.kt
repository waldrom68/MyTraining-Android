package com.rome.tech.mytraining.todo_app

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rome.tech.mytraining.R
import com.rome.tech.mytraining.todo_app.adapter.CategoriesAdapter
import com.rome.tech.mytraining.todo_app.adapter.TaskAdapter
import com.rome.tech.mytraining.todo_app.model.Task
import com.rome.tech.mytraining.todo_app.model.TaskCategory


class TodoMainActivity : AppCompatActivity() {
    //    TODO refactoring Hardcoded rules
//    por ahora es fija
    private val categories = listOf(
        TaskCategory.Business,
        TaskCategory.Personal,
        TaskCategory.Domestic,
        TaskCategory.Other
    )

    private val tasks = mutableListOf<Task>(
        Task("Preparar mates", TaskCategory.Domestic, false),
        Task("Buscar a Xime", TaskCategory.Domestic, false),
        Task("Analisis", TaskCategory.Personal, false),
        Task("Souvenires", TaskCategory.Personal, false),
        Task("Comprar remedios", TaskCategory.Domestic, false),
    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var rvTasks: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var fabAddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_main)

        initComponents()
        initListeners()
        initUI()

    }

    private fun showDialog() {
        // TODO ver por que no cambia el color de fondo de la pantalla principal
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_add_task)

        // Obtengo los elementos del dialogo que voy a necesitar
        val todo_etTask: EditText = dialog.findViewById(R.id.todo_etTask)
        val todo_btnNewTask: Button = dialog.findViewById(R.id.todo_btnNewTask)
        val todo_rbgCategories: RadioGroup = dialog.findViewById(R.id.todo_rbgCategories)

        todo_btnNewTask.setOnClickListener {

            val currentTask: String = todo_etTask.text.toString()

            if (currentTask.isNotEmpty()) {

                val selectedId = todo_rbgCategories.checkedRadioButtonId
                // Lo busco dentro de la lista de los radiusbutton de categorias
                val selectedRadioButton: RadioButton = todo_rbgCategories.findViewById(selectedId)

                val currentCategory: TaskCategory =
                    // TODO refactoring HardCode referenciado a res/string
                    when (selectedRadioButton.text) {
                        getString(R.string.todo_business) -> TaskCategory.Business
                        getString(R.string.todo_personal) -> TaskCategory.Personal
                        getString(R.string.todo_domestic) -> TaskCategory.Domestic
                        else -> {
                            TaskCategory.Other
                        }
                    }
                tasks.add(Task(todo_etTask.text.toString(), currentCategory, false))
                // ahora ha que avisar al adapter view que la lista ha sido actualizada
                updateTasks()
                dialog.hide()
            }

        }
        dialog.show()
    }

    private fun initComponents() {
        rvCategories = findViewById(R.id.todo_rvCategories)
        rvTasks = findViewById(R.id.todo_rvTasks)
        fabAddTask = findViewById<FloatingActionButton>(R.id.todo_fabAddTask)

    }

    private fun initUI() {

        // Listado de categorias de Tareas
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        // Listado de tareas
        /* entre llaves paso la funcion que se ejecutara como lamda en cada item */
        taskAdapter = TaskAdapter( tasks ) { onItemSelected(it) }

        rvTasks.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTasks.adapter = taskAdapter

    }

    private fun initListeners() {
        fabAddTask.setOnClickListener {
            showDialog()
        }
    }

    private fun onItemSelected(position: Int) {
        tasks[position]._isSelected = !tasks[position]._isSelected
        updateTasks()
    }

    private fun updateTasks() {
        /* TODO no es lo ideal, esto consume muchos recursos al recrear la lista completa
            debiera notificarse al adapter insercion, modificacion o eliminacion */
        taskAdapter.notifyDataSetChanged()
    }
}