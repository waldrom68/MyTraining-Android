package com.rome.tech.mytraining

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.rome.tech.mytraining.IMCapp.ImcMainActivity
import com.rome.tech.mytraining.settings.SettingsActivity
import com.rome.tech.mytraining.superheros_app.SuperHerosListActivity
import com.rome.tech.mytraining.todo_app.TodoMainActivity

class MenuActivity : AppCompatActivity() {

//  acceso: "https://superheroapi.com/api/1531095577640835/search/{name}"
    private lateinit var btnIMCApp: Button
    private lateinit var btnTodoApp: Button
    private lateinit var btnSuperherosApp: Button
    private lateinit var btnSettingsApp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        initElements()
        initListeners()

//        initUI()

    }

    private fun initElements() {
        btnIMCApp = findViewById<Button>(R.id.btnIMCApp)
        btnTodoApp = findViewById<Button>(R.id.btnTODO)
        btnSuperherosApp = findViewById<Button>(R.id.btnSuperherosApp)
        btnSettingsApp = findViewById<Button>(R.id.btnSettingsApp)

    }

    private fun initListeners() {
        btnIMCApp.setOnClickListener { navigateToIMCApp() }
        btnTodoApp.setOnClickListener { navigateToTODOApp() }
        btnSuperherosApp.setOnClickListener { navigateToSUPERHEROSApp() }
        btnSettingsApp.setOnClickListener { navigateToSettingsApp() }

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

    private fun navigateToSUPERHEROSApp() {
        var intent = Intent(this, SuperHerosListActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSettingsApp() {
        var intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
}