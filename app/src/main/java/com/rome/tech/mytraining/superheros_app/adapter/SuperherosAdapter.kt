package com.rome.tech.mytraining.superheros_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rome.tech.mytraining.R
import com.rome.tech.mytraining.superheros_app.holder.SuperherosViewHolder
import com.rome.tech.mytraining.superheros_app.model.Superhero

class SuperherosAdapter(
    var superheros: List<Superhero> = emptyList(),
    private val onItemSelected: (String) -> Unit,
) :
    RecyclerView.Adapter<SuperherosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperherosViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        return SuperherosViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuperherosViewHolder, position: Int) {
        holder.bind(superheros[position], onItemSelected )
    }

    override fun getItemCount() = superheros.size

    fun updateList(list: List<Superhero>) {
        this.superheros = list
        this.notifyDataSetChanged()
    }
}

