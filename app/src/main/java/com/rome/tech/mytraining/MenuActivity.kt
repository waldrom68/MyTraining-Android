package com.rome.tech.mytraining

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.rome.tech.mytraining.IMCapp.IMCActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var btnIMCApp = findViewById<Button>(R.id.btnIMCApp)
        var btnOtherApp = findViewById<Button>(R.id.btnOther)

        btnIMCApp.setOnClickListener { navigateToIMCApp() }
        btnOtherApp.setOnClickListener { Log.i("wal", "Click en btnOtherApp") }

    }

    private fun navigateToIMCApp() {
        var intent = Intent(this, IMCActivity::class.java)
        startActivity(intent)
    }

}