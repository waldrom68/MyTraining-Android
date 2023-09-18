package com.rome.tech.mytraining.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rome.tech.mytraining.R
import com.rome.tech.mytraining.databinding.ActivitySettingsBinding
import com.rome.tech.mytraining.databinding.ActivitySuperHerosListBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
//        TODO("Not yet implemented")
    }

}