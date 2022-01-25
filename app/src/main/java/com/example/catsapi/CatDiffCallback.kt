package com.example.catsapi

import androidx.recyclerview.widget.DiffUtil
import com.foodrecipesapp.data.database.entities.FavoritesEntity

class CatDiffCallback: DiffUtil.ItemCallback<FavoritesEntity>() {
    override fun areItemsTheSame(
        oldItem: FavoritesEntity,
        newItem: FavoritesEntity
    ): Boolean =oldItem.id==newItem.id

    override fun areContentsTheSame(
        oldItem: FavoritesEntity,
        newItem: FavoritesEntity
    ): Boolean = oldItem.equals(newItem)
}