package com.poke.feature_poke_list.feature.detail.abilitity.presentation.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.poke.core.data.database.model.Ability
import com.poke.core.data.database.model.Move
import com.poke.feature_poke_list.databinding.ViewHolderAbilityBinding
import com.poke.feature_poke_list.databinding.ViewHolderMoveBinding
import java.util.*

class AbilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(ability: Ability) {
        val binding = ViewHolderAbilityBinding.bind(itemView)
        with(binding) {
            value.text = ability.name
        }
    }
}