package com.rome.tech.mytraining

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.rome.tech.mytraining.IMCapp.ImcMainActivity
import com.rome.tech.mytraining.TODOapp.TodoMainActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var btnIMCApp: Button
    private lateinit var btnOtherApp: Button
    private lateinit var btnTodoApp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        initElements()
        initListeners()

//        initUI()

    }

    private fun initElements() {
        btnIMCApp = findViewById<Button>(R.id.btnIMCApp)
        btnTodoApp =  findViewById<Button>(R.id.btnTODO)

        btnOtherApp = findViewById<Button>(R.id.btnOther)

    }

    private fun initListeners() {
        btnIMCApp.setOnClickListener { navigateToIMCApp() }
        btnTodoApp.setOnClickListener { navigateToTODOApp() }

        btnOtherApp.setOnClickListener { Log.i("wal", "Click en btnOtherApp") }

    }



//    private fun initUI() {
//        TODO("Not yet implemented")
//    }

    private fun navigateToIMCApp() {
        var intent = Intent(this, ImcMainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToTODOApp() {
        var intent = Intent(this, TodoMainActivity::class.java)
        startActivity(intent)
    }

}