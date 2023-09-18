package com.rome.tech.mytraining.superheros_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.rome.tech.mytraining.databinding.ActivitySuperHerosListBinding
import com.rome.tech.mytraining.superheros_app.DetailSuperheroActivity.Companion.EXTRA_ID
import com.rome.tech.mytraining.superheros_app.adapter.SuperherosAdapter
import com.rome.tech.mytraining.superheros_app.model.Superhero
import com.rome.tech.mytraining.superheros_app.model.SuperheroImageResponse
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

    companion object {
        const val URL = "https://superheroapi.com/"
    }

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

        //  TODO, elimina las imagenes cuando roto el dispositivo.
        adapter =
            SuperherosAdapter() { navigateToDetail(it) }  // aqui le estoy pasando el metodo sin el id que aún no tengo

        binding.rvSuperhero.setHasFixedSize(true)
        binding.rvSuperhero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperhero.adapter = adapter

    }

    private fun searchByName(query: String) {
        // TODO, obtengo error si el servicio devuelve un valor nulo
        binding.progressBar.isVisible = true

        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getSuperheros(query.trim())

            if (myResponse.isSuccessful) {

                val response: SuperherosResponse? = myResponse.body()

                if (response?.superheros != null) {

                    runOnUiThread {
                        updateSuperherosList(response.superheros)
                        binding.progressBar.isVisible = false;
                        binding.superHelp.isVisible = false;

                    }

                } else {

                    runOnUiThread {
                        Log.i("SEMILLA searchByName", "busqueda sin datos")
                        updateSuperherosList(

                            listOf(
                                Superhero(
                                    "1", "Sin datos", SuperheroImageResponse("")
                                )
                            )
                        )
                        binding.progressBar.isVisible = false
                        binding.superHelp.isVisible = false;
                    }
                }
            } else {
                Log.i("SEMILLA searchByName", "error en la conexion al servidor")
                runOnUiThread {
                    binding.progressBar.isVisible = false
                    binding.superHelp.isVisible = false;
                }
            }

        }


    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(URL) //no olvidar ultimo slash
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun updateSuperherosList(list: List<Superhero>) {
//        Log.i("SEMILLA", "mando a actualizar: ${list.toString()}")
        adapter.updateList(list)
    }

    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}