package com.poke.feature_poke_list.feature.list.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.poke.core.PokeViewModel
import com.poke.core.PokeViewModel2
import com.poke.core.data.database.model.Poke
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalMaterialApi
@ExperimentalFoundationApi
class PokeList2Fragment : Fragment(){

    val viewModel by viewModel<PokeViewModel2>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                PokeList(viewModel) { selectedPoke ->
                    onSelectPoke(selectedPoke)
                }
            }
        }
    }

    private fun onSelectPoke(selectedPoke: Poke) {
        findNavController().navigate(
            PokeList2FragmentDirections.actionPokeListFragmentToPokeDetailFragment(
                selectedPoke = selectedPoke
            )
        )
    }
}

