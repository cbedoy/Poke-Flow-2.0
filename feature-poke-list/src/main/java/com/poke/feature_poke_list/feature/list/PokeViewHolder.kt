package com.poke.feature_poke_list.feature.list

import android.content.res.Resources
import android.view.View
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import com.poke.core.data.database.model.Poke
import com.poke.core.extensions.asPokeNumber
import com.poke.core.extensions.resIdByName
import com.poke.feature_poke_list.R
import com.poke.feature_poke_list.databinding.ViewHolderPokeBinding
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), KoinComponent {

    private val imageLoader by inject<ImageLoader>()

    fun bind(poke: Poke) {
        val binding = ViewHolderPokeBinding.bind(itemView)
        with(binding) {
            val widthPixels = Resources.getSystem().displayMetrics.widthPixels
            val pokeTypes = poke.type.split(",").map { it.capitalize() }
            reloadTypes(binding, types = pokeTypes)
            reloadBackground(binding, type = pokeTypes.firstOrNull())
            container.layoutParams.apply {
                width = widthPixels / GRID_COUNT
            }
            image.load(poke.svgImage, imageLoader = imageLoader) {
                crossfade(true)
            }
            name.text = poke.name
        }
    }

    private fun reloadBackground(binding: ViewHolderPokeBinding, type: String?) {
        binding.container.setBackgroundResource(binding.container.context.resIdByName(
                resIdName = type?.toLowerCase(),
                resType = "color"
        ))
    }

    private fun reloadTypes(binding: ViewHolderPokeBinding, types: List<String>) {
        with(binding) {
            when (types.size) {
                0 -> {
                    type.isInvisible = true
                    subType.isInvisible = true
                }
                1 -> {
                    type.isInvisible = false
                    subType.isInvisible = true
                    type.text = types.first()
                }
                else -> {
                    type.isInvisible = false
                    subType.isInvisible = false
                    type.text = types.first()
                    subType.text = types.last()
                }
            }
        }
    }
}