package com.poke.feature_poke_list.feature.detail.move.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.poke.feature_poke_list.R
import com.poke.feature_poke_list.databinding.FragmentStatsBinding
import com.poke.feature_poke_list.feature.detail.move.presentation.MoveViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

const val MOVE_FRAGMENT_POKE_ID = "args-stats-fragment-poke-id"

class MoveFragment : Fragment(R.layout.fragment_move){

    private val pokeId by lazy {
        arguments?.getLong(MOVE_FRAGMENT_POKE_ID)?: 0
    }

    private val viewModel by viewModel<MoveViewModel> { parametersOf(pokeId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentStatsBinding.bind(view)

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context)
        }


        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                binding.recyclerView.adapter = MoveAdapter(it)
            }
        }
    }
}