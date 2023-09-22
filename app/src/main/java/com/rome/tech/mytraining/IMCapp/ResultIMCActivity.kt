package com.rome.tech.mytraining.IMCapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.rome.tech.mytraining.IMCapp.ImcMainActivity.Companion.IMC_RESULT_KEY
import com.rome.tech.mytraining.R
import java.text.DecimalFormat

class ResultIMCActivity : AppCompatActivity() {
    lateinit var imcResult: TextView
    lateinit var imcResultStr: TextView
    lateinit var btnReCalculate: Button

    var result: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)
        // tomo el valor desde el conexto enviado de la clase padre
        result = intent.extras?.getDouble(IMC_RESULT_KEY) ?: 0.0

        initElements()
        initListeners()
        setResultado(result)

    }

    private fun initElements() {
        imcResult = findViewById<TextView>(R.id.imcResult)
        imcResultStr = findViewById<TextView>(R.id.imcResultStr)
        btnReCalculate = findViewById<Button>(R.id.btnReCalculate)
    }

    private fun initListeners() {
        btnReCalculate.setOnClickListener { onBackPressed() }
    }

    private fun setResultado(resultado: Double) {
        // TODO refactoring
        var colorFont: Int? = null
        var imcStatus = ""
        val df = DecimalFormat("#.##")

        when (resultado) {
            in 0.00..15.00 -> {  // Delgadez muy severa
                imcStatus = getString(R.string.very_severe_thinness)
                colorFont = ContextCompat.getColor(this, R.color.very_severe)
            }

            in 15.01..15.99 -> {  // Delgadez severa
                imcStatus = getString(R.string.severe_thinness)
                colorFont = ContextCompat.getColor(this, R.color.very_severe)
            }

            in 16.00..18.49 -> {  // Delgadez
                imcStatus = getString(R.string.thinness)
                colorFont = ContextCompat.getColor(this, R.color.severe)
            }

            in 18.50..24.99 -> {  // Peso sludable
                imcStatus = getString(R.string.Healthy_weight)
                colorFont = ContextCompat.getColor(this, R.color.healthy)
            }

            in 25.00..29.99 -> {  // Sobrepeso
                imcStatus = getString(R.string.over_weight)
                colorFont = ContextCompat.getColor(this, R.color.moderate)
            }

            in 30.00..34.99 -> {  // Obesidad moderada
                imcStatus = getString(R.string.moderate_obesity)
                colorFont = ContextCompat.getColor(this, R.color.severe)
            }

            in 35.00..39.99 -> {  // Obesidad severa
                imcStatus = getString(R.string.severe_obesity)
                colorFont = ContextCompat.getColor(this, R.color.very_severe)
            }

            in 40.00..999.99 -> {  // Obesidad muy severa
                imcStatus = getString(R.string.very_severe_obesity)
                colorFont = ContextCompat.getColor(this, R.color.very_severe)
            }

            else -> {
                imcStatus = getString(R.string._ErrorIMC)
                colorFont =
                    ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_error)
            }

        }

        imcResult.text = df.format(result).toDouble().toString()
        imcResultStr.text = imcStatus
        imcResultStr.setTextColor(colorFont)
    }

}