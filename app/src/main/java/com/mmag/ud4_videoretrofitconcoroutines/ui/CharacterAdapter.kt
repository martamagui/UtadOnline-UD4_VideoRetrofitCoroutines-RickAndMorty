package com.mmag.ud4_videoretrofitconcoroutines.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mmag.ud4_videoretrofitconcoroutines.R
import com.mmag.ud4_videoretrofitconcoroutines.databinding.ItemCharacterBinding
import com.mmag.ud4_videoretrofitconcoroutines.data.network.model.Character
import com.mmag.ud4_videoretrofitconcoroutines.utils.loadFromUrl

class CharacterAdapter() :
    ListAdapter<Character, CharacterAdapter.CharacterAdapterViewHolder>(CharacterItemCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
        return CharacterAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterAdapterViewHolder, position: Int) {
        val character = getItem(position)
        holder.binding.tvCharacterName.text = character.name

        holder.binding.ivCharacterPhoto.loadFromUrl(character.image, holder.binding.root.context)
    }


    inner class CharacterAdapterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)
}

object CharacterItemCallBack : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

}