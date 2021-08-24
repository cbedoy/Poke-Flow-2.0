package com.poke.feature_poke_list.feature.detail.stats.presentation.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.poke.core.data.database.model.Stat
import com.poke.feature_poke_list.databinding.ViewHolderStatBinding
import java.util.*

class StatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(stat: Stat) {
        val binding = ViewHolderStatBinding.bind(itemView)
        with(binding) {
            title.text = stat.name.split("-").joinToString(separator = " ") {
                it.capitalize(Locale.ROOT)
            }
            value.text = "${stat.value}"
            progressValue.progress = stat.value
        }
    }
}