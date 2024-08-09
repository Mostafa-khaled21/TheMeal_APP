package com.example.themeal_app.Data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themeal_app.DatabaseModel.model.Meal
import com.example.themeal_app.R

class RandomImageAdapter(private val context: Context) : RecyclerView.Adapter<RandomImageAdapter.ImageViewHolder>() {

    private var imageList: List<Meal> = listOf()

    fun setImageList(images: List<Meal>?) {
        imageList = images ?: listOf()
        notifyDataSetChanged()
    }


    class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image_view)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.designhomescroll, parent, false)
        return ImageViewHolder(layout)
    }


    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val meal = imageList[position]
        Glide.with(context)
            .load(meal.strMealThumb)
            .centerCrop()
            .into(holder.image)
    }
}