package com.example.catsapi.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.catsapi.data.local.entities.FavoritesEntity

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