package com.poke.feature_poke_list.feature.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PokeDetailPageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        return AboutDetailFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Title $position"
    }
}