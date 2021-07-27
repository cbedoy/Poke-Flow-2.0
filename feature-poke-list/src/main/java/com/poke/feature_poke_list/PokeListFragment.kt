package com.poke.feature_poke_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.poke.core.PokeViewModel
import com.poke.feature_poke_list.databinding.FragmentPokeListBinding
import org.koin.android.viewmodel.ext.android.viewModel

class PokeListFragment : Fragment(R.layout.fragment_poke_list) {

    private val viewModel by viewModel<PokeViewModel>()
    private val pokeListAdapter = PokeListAdapter {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentPokeListBinding.bind(view)

        with(binding) {
            with(recyclerView) {
                layoutManager = GridLayoutManager(context, GRID_COUNT)
                adapter = pokeListAdapter
            }
        }

        viewModel.pokeLiveData.observe(
            viewLifecycleOwner,
            {
                pokeListAdapter.submitList(it)
            }
        )
    }
}