package com.app.lezzet.ui.adapter.viewpageradapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(
    activity: FragmentActivity?,
    private val fragments: ArrayList<Fragment>,
    private val titles: ArrayList<String>,
    private val bundle: Bundle
) :
    FragmentStateAdapter(activity!!) {


    fun getTabTitle(position: Int): String {
        return titles[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        fragments[position].arguments = bundle
        return fragments[position]
    }
}