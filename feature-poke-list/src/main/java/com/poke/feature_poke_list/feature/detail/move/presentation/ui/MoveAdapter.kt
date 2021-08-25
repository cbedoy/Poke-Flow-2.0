package com.poke.feature_poke_list.feature.detail.move.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.poke.core.data.database.model.Move
import com.poke.feature_poke_list.R
import com.poke.feature_poke_list.feature.detail.abilitity.presentation.ui.AbilityViewHolder

class MoveAdapter (private val moves: List<Move>) : RecyclerView.Adapter<MoveViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder {
        return MoveViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_move, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        holder.bind(moves[position])
    }

    override fun getItemCount(): Int {
        return moves.size
    }
}