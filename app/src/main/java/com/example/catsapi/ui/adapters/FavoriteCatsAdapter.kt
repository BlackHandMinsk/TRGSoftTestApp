package com.example.catsapi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catsapi.utils.CatDiffCallback
import com.example.catsapi.databinding.CatsListItemBinding
import com.example.catsapi.utils.placePictureInView
import com.example.catsapi.data.local.entities.FavoritesEntity
import com.example.catsapi.ui.fragments.FavoriteCatsFragmentDirections


class FavoriteCatsAdapter : ListAdapter<FavoritesEntity, FavoriteCatsAdapter.MainViewHolder>(
    CatDiffCallback()
) {

    class MainViewHolder(val binding: CatsListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            CatsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onViewAttachedToWindow(holder: MainViewHolder) {
        holder.itemView.setOnClickListener {
            val action =
                FavoriteCatsFragmentDirections.actionFavoriteCatsFragment2ToCatInfoFragment(
                    currentList[holder.bindingAdapterPosition].url
                )
            holder.itemView.findNavController().navigate(action)
        }
    }


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val ivCatMain: ImageView = holder.binding.catImage
        placePictureInView(ivCatMain, currentList[holder.bindingAdapterPosition].url)
    }
}