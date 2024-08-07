package com.example.viewmodel.products.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themeal_app.DatabaseModel.model.Meal
import com.example.themeal_app.DatabaseModel.model.allData
import com.example.themeal_app.R

class searchAdapter(private val context: Context) : RecyclerView.Adapter<searchAdapter.MyViewHolder>() {
    lateinit var allData: allData

    fun submitData(newAllData: allData) {
        allData = newAllData
        notifyDataSetChanged()
    }

    class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val title: TextView = row.findViewById(R.id.product_title)
        val img: ImageView = row.findViewById(R.id.product_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.search_card, parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = allData.meals.get(position)
        holder.title.text = category.strMeal

        Glide.with(context)
            .load(category.strMealThumb)
            .centerCrop()
            .into(holder.img)
    }


    override fun getItemCount(): Int {
        return allData.meals.size
    }


}