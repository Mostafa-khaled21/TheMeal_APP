package com.example.themeal_app.Data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal_app.R

class favAdapter (
    private val recipes: List<String>,
    private val onRemoveClick: (String) -> Unit
) : RecyclerView.Adapter< favAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeName: TextView = itemView.findViewById(R.id.recipeName)
        private val removeButton: Button = itemView.findViewById(R.id.removeButton)

        fun bind(recipe: String) {
            recipeName.text = recipe
            removeButton.setOnClickListener {
                onRemoveClick(recipe)
            }
        }
    }
}
