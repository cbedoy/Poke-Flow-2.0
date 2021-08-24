package com.poke.feature_poke_list.feature.detail.move.presentation.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.poke.core.data.database.model.Move
import com.poke.feature_poke_list.databinding.ViewHolderMoveBinding
import java.util.*

class MoveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(move: Move) {
        val binding = ViewHolderMoveBinding.bind(itemView)
        with(binding) {
            value.text = move.name.split("-").joinToString(separator = " ") {
                it.capitalize(Locale.ROOT)
            }
        }
    }
}