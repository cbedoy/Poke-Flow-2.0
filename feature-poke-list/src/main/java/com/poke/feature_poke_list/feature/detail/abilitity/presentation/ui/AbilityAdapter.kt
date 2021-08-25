package com.poke.feature_poke_list.feature.detail.abilitity.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.poke.core.data.database.model.Ability
import com.poke.core.data.database.model.Move
import com.poke.feature_poke_list.R

class AbilityAdapter (private val ability: List<Ability>) : RecyclerView.Adapter<AbilityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        return AbilityViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_ability, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        holder.bind(ability[position])
    }

    override fun getItemCount(): Int {
        return ability.size
    }
}