package com.poke.feature_poke_list.feature.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.tabs.TabLayoutMediator
import com.poke.core.data.database.model.Poke
import com.poke.core.extensions.asPokeNumber
import com.poke.core.extensions.resIdByName
import com.poke.feature_poke_list.R
import com.poke.feature_poke_list.databinding.FragmentPokeDetailBinding
import java.util.*

class PokeDetailFragment : Fragment(R.layout.fragment_poke_detail){

    private val args: PokeDetailFragmentArgs by navArgs()

    private val selectedPoke: Poke
        get() = args.selectedPoke

    private val pokeId: Long
        get() = args.selectedPoke.number

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentPokeDetailBinding.bind(view)

        with(binding) {
            val pokeTypes = selectedPoke.type.split(",").map { it.capitalize() }
            reloadTypes(binding, types = pokeTypes)
            reloadBackground(binding, type = pokeTypes.firstOrNull())
            image.load(selectedPoke.image) {
                crossfade(true)
            }
            name.text = selectedPoke.name
            number.text = selectedPoke.number.asPokeNumber

            val pokeDetailPageAdapter = PokeDetailPageAdapter(pokeId, this@PokeDetailFragment)
            pager.adapter = pokeDetailPageAdapter
        }
    }

    private fun reloadBackground(binding: FragmentPokeDetailBinding, type: String?) {
        binding.container.setBackgroundResource(binding.container.context.resIdByName(
            resIdName = type?.toLowerCase(Locale.ROOT),
                resType = "color"
        ))
    }

    private fun reloadTypes(binding: FragmentPokeDetailBinding, types: List<String>) {
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