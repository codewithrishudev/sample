package com.codewtihrishu.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.codewtihrishu.fragment.HistoryFragment
import com.codewtihrishu.fragment.VideoFragment


internal class ViewStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {

        return if (position == 0) {
            VideoFragment()
        } else HistoryFragment()
    }

    override fun getItemCount(): Int {

        return 2
    }
}