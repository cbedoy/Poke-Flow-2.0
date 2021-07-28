package com.poke.feature_poke_list.feature.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.poke.core.data.database.model.Poke
import com.poke.feature_poke_list.R

class PokeListAdapter (
    private val onSelectedPoke: (Poke) -> Unit
        ): PagedListAdapter<Poke, PokeViewHolder>(PokeListAdapterDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        return PokeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_poke,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        getItem(position)?.let { poke ->
            holder.bind(poke)
            holder.itemView.setOnClickListener {
                onSelectedPoke(poke)
            }
        }
    }
}

object PokeListAdapterDiffUtil: DiffUtil.ItemCallback<Poke>() {
    override fun areItemsTheSame(oldItem: Poke, newItem: Poke): Boolean {
        return oldItem.number == newItem.number
    }

    override fun areContentsTheSame(oldItem: Poke, newItem: Poke): Boolean {
        return oldItem == newItem
    }

}