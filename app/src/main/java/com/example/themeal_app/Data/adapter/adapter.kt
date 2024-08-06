package com.example.viewmodel.products.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themeal_app.R
import com.example.themeal_app.model.allData

class adapter(private val context: Context) : RecyclerView.Adapter<adapter.MyViewHolder>() {
   lateinit var  data:allData

    fun setCategoryList(categoryList: allData){
        this.data = categoryList
        notifyDataSetChanged()
    }

    class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val title: TextView = row.findViewById(R.id.product_title)
        val desc: TextView = row.findViewById(R.id.product_desc)
        val img: ImageView = row.findViewById(R.id.product_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return MyViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return data.categories.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = data.categories[position]
        holder.title.text = category.strCategory
        holder.desc.text = category.strCategoryDescription
        Glide.with(context)
            .load(category.strCategoryThumb)
            .centerCrop()
            .into(holder.img)
    }
}