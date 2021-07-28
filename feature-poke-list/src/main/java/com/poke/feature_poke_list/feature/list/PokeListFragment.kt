package com.poke.feature_poke_list.feature.list

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.poke.core.PokeViewModel
import com.poke.feature_poke_list.R
import com.poke.feature_poke_list.databinding.FragmentPokeListBinding
import org.koin.android.viewmodel.ext.android.viewModel

class PokeListFragment : Fragment(R.layout.fragment_poke_list) {

    private val viewModel by viewModel<PokeViewModel>()
    private val pokeListAdapter = PokeListAdapter {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
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

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_search, menu)
        // Associate searchable configuration with the SearchView
        val searchManager = requireContext().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as? SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.onTextChange(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onTextChange(newText)
                return false
            }
        })
    }
}