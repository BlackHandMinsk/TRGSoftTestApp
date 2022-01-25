package com.example.catsapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catsapi.CatDiffCallback
import com.example.catsapi.databinding.CatsListItemBinding
import com.example.catsapi.placePictureInView
import com.example.catsapi.ui.FavoriteCatsFragmentDirections
import com.foodrecipesapp.data.database.entities.FavoritesEntity


class FavoriteCatsAdapter: ListAdapter<FavoritesEntity, FavoriteCatsAdapter.MainViewHolder>(
    CatDiffCallback()
) {

    class MainViewHolder(val binding: CatsListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(CatsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onViewAttachedToWindow(holder: MainViewHolder) {
        holder.itemView.setOnClickListener {
            val action  =
                FavoriteCatsFragmentDirections.actionFavoriteCatsFragment2ToCatInfoFragment(currentList[holder.bindingAdapterPosition].url)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun onViewDetachedFromWindow(holder: MainViewHolder) {
        super.onViewDetachedFromWindow(holder)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val ivCatMain: ImageView = holder.binding.catImage
        placePictureInView(ivCatMain,currentList[holder.bindingAdapterPosition].url)
    }
}