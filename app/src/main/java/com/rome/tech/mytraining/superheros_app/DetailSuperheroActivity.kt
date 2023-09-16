package com.rome.tech.mytraining.superheros_app

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rome.tech.mytraining.databinding.ActivityDetailSuperheroBinding
import com.rome.tech.mytraining.superheros_app.model.Powerstats
import com.rome.tech.mytraining.superheros_app.model.SuperheroDetailResponse
import com.rome.tech.mytraining.superheros_app.service.ApiService
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperheroActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailSuperheroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail_superhero)
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupero el parametro que se le está enviando
        val id = intent.getStringExtra(EXTRA_ID).orEmpty()
        viewDetail(id)

    }

    private fun viewDetail(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse =
                getRetrofit().create(ApiService::class.java).getSuperheroDetail(id.trim())

            if (myResponse.isSuccessful) {

                val response: SuperheroDetailResponse? = myResponse.body()
                Log.i("SEMILLA", response.toString())
                if (response?.powerstats != null) {

                    runOnUiThread {
                        createUI(response)
                    }

                } else {
                    runOnUiThread {
                        Log.i("SEMILLA viewDetail", "busqueda sin datos")
                    }
                }
            } else {
                Log.i("SEMILLA viewDetail", "error en la conexion al servidor")
                runOnUiThread { }
            }
        }

    }

    private fun createUI(superhero: SuperheroDetailResponse) {
        Picasso.get()
            .load(superhero.superheroImage.superheroImgUrl)
            .into(binding.ivSuperhero)

        binding.tvSuperheroName.text = superhero.name
        powerStats(superhero.powerstats)

    }


    private fun powerStats(superStats: Powerstats) {
        updateHeight(binding.viewIntelligence, superStats.intelligence)
        updateHeight(binding.viewStrength, superStats.strength)
        updateHeight(binding.viewSpeed, superStats.speed)
        updateHeight(binding.viewDurability, superStats.durability)
        updateHeight(binding.viewPower, superStats.power)
        updateHeight(binding.viewCombat, superStats.combat)
    }

    private fun pxToDp(px: Float): Int {
        // ajusto las dimensiones de acuerdo a los pixeles del dispositivo
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics)
            .roundToInt()
    }

    private fun updateHeight(view: View, height: String) {
        // Esta funcion recibe un elemento de la vista y el nuevo valor de su altura
        // parametros del componente
        val params = view.layoutParams
        var value: Float? = height.toFloatOrNull()

        if (value != null) {
            params.height = pxToDp(value)

        } else {
            params.height = pxToDp(0.toFloat())
        }

        view.layoutParams = params
    }

    private fun getRetrofit(): Retrofit {
        // TODO refactorizar para no repetir este codigo en cada lugar que se requiere su uso
        return Retrofit.Builder().baseUrl(SuperHerosListActivity.URL) //no olvidar ultimo slash
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}