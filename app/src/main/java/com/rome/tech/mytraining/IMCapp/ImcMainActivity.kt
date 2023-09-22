package com.rome.tech.mytraining.IMCapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.rome.tech.mytraining.R
import java.text.DecimalFormat

/*
Calculadora del índice de masa corporal (IMC)

El sobrepeso puede causar la elevación de la concentración de colesterol total y de la presión arterial, y aumentar el riesgo de sufrir
la enfermedad arterial coronaria. La obesidad aumenta las probabilidades de que se presenten otros factores de riesgo cardiovascular,
en especial, presión arterial alta, colesterol elevado y diabetes.



*/

class ImcMainActivity : AppCompatActivity() {
    // Initial values
    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentHeight: Int = 0

    private var currentWeight: Int = 60
    private var currentAge: Int = 25

    private lateinit var cardMale: CardView
    private lateinit var cardFemale: CardView
    private lateinit var rgHeight: RangeSlider
    private lateinit var textHeight: TextView

    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnAddWeight: FloatingActionButton
    private lateinit var textWeight: TextView

    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnAddAge: FloatingActionButton
    private lateinit var textAge: TextView

    private lateinit var btnCalculate: Button

    // Se crea una contante a la cual se accede desde otroa activities del proyecto
    companion object {
        const val IMC_RESULT_KEY = "IMC_RESULT"
        const val IMC_RESULT_STATUS_KEY = "IMC_STATUS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.imc_activity_main)

        initElements()
        initListeners()

        initUI()

    }

    private fun initElements() {
        cardMale = findViewById<CardView>(R.id.cardMale)
        cardFemale = findViewById<CardView>(R.id.cardFemale)

        rgHeight = findViewById<RangeSlider>(R.id.rgHeight)
        textHeight = findViewById<TextView>(R.id.height)
        currentHeight = if (currentHeight > 0) currentHeight else rgHeight.valueFrom.toInt()

        textWeight = findViewById(R.id.textWeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnAddWeight = findViewById(R.id.btnAddWeight)

        textAge = findViewById(R.id.textAge)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnAddAge = findViewById(R.id.btnAddAge)

        btnCalculate = findViewById<Button>(R.id.btnCalculate)

    }

    private fun initListeners() {
        cardMale.setOnClickListener {
            if (!isMaleSelected) {
                selectMale()
            }
        }
        cardFemale.setOnClickListener {
            if (!isFemaleSelected) {
                selectFemale()
            }
        }

        rgHeight.addOnChangeListener { _, value, _ -> setHeight(value) }

        btnSubtractWeight.setOnClickListener { subtractWeight() }
        btnAddWeight.setOnClickListener { addWeight() }

        btnSubtractAge.setOnClickListener { subtractAge() }
        btnAddAge.setOnClickListener { addAge() }

        btnCalculate.setOnClickListener {
            navigateToResult(imcCalculate())
        }
    }

    private fun navigateToResult(imcCalculate: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        // Le paso el valor del calculo en su contexto para tomarlo en la actividad ResultIMCActivity
        intent.putExtra(IMC_RESULT_KEY, imcCalculate)
        intent.putExtra( IMC_RESULT_STATUS_KEY ,"evaluateResult(imcCalculate)")

        startActivity(intent)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }

    private fun setGenderColor() {
        cardMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        cardFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun setHeight(valueIn: Float) {
        val df = DecimalFormat("#.##")
        val newValue: String = "${df.format(valueIn.toInt())} cm"
        currentHeight = valueIn.toInt()

        textHeight.text = newValue
    }

    private fun setWeight() {
        textWeight.text = currentWeight.toString()
    }

    private fun setAge() {
        textAge.text = currentAge.toString()
    }

    private fun selectMale() {
        isMaleSelected = true
        isFemaleSelected = false
        setGenderColor()
    }

    private fun selectFemale() {
        isMaleSelected = false
        isFemaleSelected = true
        setGenderColor()
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {

        return if (isSelectedComponent) {
            ContextCompat.getColor(this, R.color.colorPrimaryContainerSelected)
        } else {
            ContextCompat.getColor(this, R.color.colorPrimaryContainerUnSelected)
        }

    }

    private fun subtractWeight() {
        currentWeight -= 1
        setWeight()
    }

    private fun addWeight() {
        currentWeight += 1
        setWeight()
    }

    private fun subtractAge() {
        currentAge -= 1
        setAge()
    }

    private fun addAge() {
        currentAge += 1
        setAge()
    }

    private fun imcCalculate(): Double {
        // (IMC = peso (kg)/ [estatura (m)]2
        val df = DecimalFormat("#.##")
        var heightInMts = currentHeight.toDouble() / 100

        return df.format(currentWeight / (heightInMts * heightInMts)).toDouble()

    }

//    private fun evaluateResult(imcValue: Double): String {
//        val resultado = (imcValue * 100).toInt()
//
//        return when (resultado) {
//            in 1..1850 -> getString(R.string.peso_inferior_al_normal)
//            in 1851..2499 -> getString(R.string.normal)
//            in 2500..2990 -> getString(R.string.peso_superior_al_normal)
//            in 2991..9999 -> getString(R.string.obesidad)
//            else -> getString(R.string._ErrorIMC)
//        }
//    }
}