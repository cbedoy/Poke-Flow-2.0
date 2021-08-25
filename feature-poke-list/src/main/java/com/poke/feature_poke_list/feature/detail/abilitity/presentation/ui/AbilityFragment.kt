package com.poke.feature_poke_list.feature.detail.abilitity.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.poke.feature_poke_list.R
import com.poke.feature_poke_list.databinding.FragmentStatsBinding
import com.poke.feature_poke_list.feature.detail.abilitity.presentation.AbilityViewModel
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

const val ABILITY_FRAGMENT_POKE_ID = "args-stats-fragment-poke-id"

class AbilityFragment : Fragment(R.layout.fragment_move){

    private val pokeId by lazy {
        arguments?.getLong(ABILITY_FRAGMENT_POKE_ID)?: 0
    }

    private val viewModel by viewModel<AbilityViewModel> { parametersOf(pokeId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentStatsBinding.bind(view)

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context)
        }

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                binding.recyclerView.adapter = AbilityAdapter(it)
            }
        }
    }
}