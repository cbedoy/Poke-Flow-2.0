package com.poke.feature_poke_list

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.poke.core.data.database.model.Poke
import com.poke.feature_poke_list.databinding.ViewHolderPokeBinding

class PokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(poke: Poke) {
        val binding = ViewHolderPokeBinding.bind(itemView)
        with(binding) {
            val widthPixels = Resources.getSystem().displayMetrics.widthPixels
            cardContainer.layoutParams.apply {
                width = widthPixels / GRID_COUNT
            }
            image.load(poke.image) {
                crossfade(true)
            }
            name.text = poke.name
        }
    }
}