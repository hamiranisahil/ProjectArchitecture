package com.example.library.util

import android.graphics.drawable.Drawable
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager


class ViewPagerAdapter(
    fragmentManager: FragmentManager?,
    tabLayout: TabLayout,
    viewPager: ViewPager,
    val fragmentList: List<Fragment>,
    val fragmentTitleList: List<String>,
    fragmentIconList: List<Drawable>?
) : FragmentPagerAdapter(fragmentManager) {

    init {
        if (fragmentIconList != null) {
            for (i in 0 until fragmentList.size) {
                tabLayout.getTabAt(i)!!.icon = fragmentIconList[i]
            }
        }
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }
}