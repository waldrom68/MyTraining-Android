package com.rome.tech.mytraining.superheros_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.rome.tech.mytraining.databinding.ActivitySuperHerosListBinding
import com.rome.tech.mytraining.superheros_app.service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHerosListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperHerosListBinding
    private lateinit var retrofit: Retrofit

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

    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java)
                .getSuperheros(query.trim())

            if (myResponse.isSuccessful) {
                Log.i("SERVICE API", myResponse.body()?.listDataTarget.toString())

            } else {
                Log.i("SERVICE API", "Respuesta vacia")
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/api/") //no olvidar ultimo slash
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}