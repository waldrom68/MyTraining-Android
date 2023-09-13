package com.rome.tech.mytraining.superheros_app.holder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rome.tech.mytraining.databinding.ItemSuperheroBinding
import com.rome.tech.mytraining.superheros_app.model.Superhero

class SuperherosViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private var binding = ItemSuperheroBinding.bind(view)

    fun bind(superhero: Superhero) {
        Log.i("ViewHolder", superhero.superheroName)
        binding.tvSuperheroName.text = superhero.superheroName
    }
}