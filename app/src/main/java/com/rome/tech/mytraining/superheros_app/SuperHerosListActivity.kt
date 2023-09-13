package com.rome.tech.mytraining.superheros_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.rome.tech.mytraining.databinding.ActivitySuperHerosListBinding
import com.rome.tech.mytraining.superheros_app.adapter.SuperherosAdapter
import com.rome.tech.mytraining.superheros_app.model.Superhero
import com.rome.tech.mytraining.superheros_app.model.SuperherosResponse
import com.rome.tech.mytraining.superheros_app.service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHerosListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperHerosListBinding
    private lateinit var retrofit: Retrofit

    private lateinit var adapter: SuperherosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_super_heros_list)
        binding = ActivitySuperHerosListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = getRetrofit()
        initUI()

    }

    private fun initUI() {
        binding.herosSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        adapter = SuperherosAdapter()
        binding.rvSuperhero.setHasFixedSize(true)
        binding.rvSuperhero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperhero.adapter = adapter


    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true

        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getSuperheros(query.trim())

            if (myResponse.isSuccessful) {
                val response: SuperherosResponse? = myResponse.body()
                Log.i("SEMILLA", "searchByName")

                if (response != null) {
                    runOnUiThread {

                        binding.progressBar.isVisible = false
                        Log.i(
                            "SEMILLA", "obtengo este listado: ${response.superheros}"
                        )
                        updateSuperherosList(response.superheros)
                    }
                }

            } else {
                Log.i("SERVICE API", "Respuesta vacia")
                runOnUiThread {
                    binding.progressBar.isVisible = false
                }
            }

        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://superheroapi.com/api/") //no olvidar ultimo slash
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun updateSuperherosList(list: List<Superhero>) {
        Log.i("SEMILLA", "mando a actualizar: ${list.toString()}")
        adapter.updateList(list)
    }
}