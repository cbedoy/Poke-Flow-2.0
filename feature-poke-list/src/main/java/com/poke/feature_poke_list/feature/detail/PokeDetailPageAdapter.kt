package com.poke.feature_poke_list.feature.detail

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.poke.feature_poke_list.feature.detail.move.presentation.ui.MOVE_FRAGMENT_POKE_ID
import com.poke.feature_poke_list.feature.detail.move.presentation.ui.MoveFragment
import com.poke.feature_poke_list.feature.detail.stats.presentation.ui.STATS_FRAGMENT_POKE_ID
import com.poke.feature_poke_list.feature.detail.stats.presentation.ui.StatsFragment

class PokeDetailPageAdapter(private val pokeId: Long, fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            MoveFragment().apply {
                arguments = bundleOf(MOVE_FRAGMENT_POKE_ID to pokeId)
            }
        }else {
            StatsFragment().apply {
                arguments = bundleOf(STATS_FRAGMENT_POKE_ID to pokeId)
            }
        }
    }

}