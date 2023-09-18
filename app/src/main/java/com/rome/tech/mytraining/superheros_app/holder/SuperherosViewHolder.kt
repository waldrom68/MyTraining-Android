package com.rome.tech.mytraining.superheros_app.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rome.tech.mytraining.databinding.ItemSuperheroBinding
import com.rome.tech.mytraining.superheros_app.model.Superhero
import com.squareup.picasso.Picasso

class SuperherosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var binding = ItemSuperheroBinding.bind(view)

    fun bind(superhero: Superhero, onItemSelected: (String) -> Unit) {
        // aqui estoy armando cada itemview, tengo acceso a todos sus elementos como para colocar listeners
        binding.tvSuperheroName.text = superhero.superheroName

        if (superhero.superheroImage.superheroImgUrl != "") {
            /* TODO exception control
                generate notImage.png  */
            Picasso.get()
                .load(superhero.superheroImage.superheroImgUrl)
                .into(binding.ivSuperhero)
        }

        // root es el objeto creado
        binding.root.setOnClickListener{onItemSelected(superhero.superheroId)}

    }
}