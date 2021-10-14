package com.poke.feature_poke_list.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.poke.core.data.database.model.Poke
import com.poke.feature_poke_list.feature.detail.compose.PokeDetailView

class PokeDetailFragment : Fragment(){

    private val args: PokeDetailFragmentArgs by navArgs()

    private val selectedPoke: Poke
        get() = args.selectedPoke

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            PokeDetailView(poke = selectedPoke)
        }
    }

}