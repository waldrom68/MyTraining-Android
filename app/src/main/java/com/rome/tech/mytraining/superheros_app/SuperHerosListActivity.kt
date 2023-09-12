package com.rome.tech.mytraining.superheros_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.rome.tech.mytraining.databinding.ActivitySuperHerosListBinding

class SuperHerosListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperHerosListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySuperHerosListBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_super_heros_list)
        setContentView(binding.root)

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
        Log.i("busqueda", "Click en submit ${query.trim()}-")
    }

}