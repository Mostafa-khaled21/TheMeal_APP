package com.example.viewmodel.products.adapter

import Category
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themeal_app.DatabaseModel.model.Meal
import com.example.themeal_app.R
import com.example.themeal_app.DatabaseModel.model.allData

class adapter(private val context: Context) : RecyclerView.Adapter<adapter.MyViewHolder>() {

    private var data: allData? = null

    fun setCategoryList(categoryList: allData) {
        this.data = categoryList
        notifyDataSetChanged()
    }

    class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val title: TextView = row.findViewById(R.id.product_title)
        val img: ImageView = row.findViewById(R.id.product_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return MyViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return data?.categories?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = data?.categories?.get(position) ?: return

        holder.title.text = category.strCategory
        Glide.with(context)
            .load(category.strCategoryThumb)
            .centerCrop()
            .into(holder.img)
    }
}
