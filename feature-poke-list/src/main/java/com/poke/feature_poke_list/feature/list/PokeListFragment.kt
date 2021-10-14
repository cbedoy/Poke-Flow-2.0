package com.poke.feature_poke_list.feature.list

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.poke.core.PokeViewModel
import com.poke.feature_poke_list.R
//import com.poke.feature_poke_list.databinding.FragmentPokeListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokeListFragment : Fragment(R.layout.fragment_poke_list) {

    private val viewModel by viewModel<PokeViewModel>()
    private val pokeListAdapter = PokeListAdapter {
        //findNavController().navigate(
                //PokeListFragmentDirections.actionPokeListFragmentToPokeDetailFragment(
                //        selectedPoke = it
                //)
        //)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
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
        )*/
    }


}