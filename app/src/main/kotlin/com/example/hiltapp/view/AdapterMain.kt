package com.example.hiltapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.hiltapp.R
import com.example.hiltapp.databinding.ItemMealBinding
import com.example.hiltapp.model.ModelMeal

class AdapterMain : RecyclerView.Adapter<AdapterMain.MyViewHolder>() {

    private var dataList = emptyList<ModelMeal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemMealBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    fun setData(data: List<ModelMeal>) {
        dataList = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataList.size

    class MyViewHolder(private val binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModelMeal) {
            binding.textView.text = data.strMeal

            binding.imageView.load(data.strMealThumb) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
                transformations(CircleCropTransformation())
            }
        }
    }
}
