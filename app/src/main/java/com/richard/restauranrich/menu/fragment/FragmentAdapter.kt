package com.richard.restauranrich.menu.fragment

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter (fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MakananFragment()
            1 -> MinumanFragment()
            2 -> DessertFragment()

            else -> throw Resources.NotFoundException("Position Not Fond")
        }
    }
}