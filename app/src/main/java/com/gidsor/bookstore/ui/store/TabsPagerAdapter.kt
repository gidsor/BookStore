package com.gidsor.bookstore.ui.store

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.gidsor.bookstore.BookStoreApplication
import com.gidsor.bookstore.R
import com.gidsor.bookstore.ui.store.novelties.NoveltiesFragment
import com.gidsor.bookstore.ui.store.popular.PopularFragment
import com.gidsor.bookstore.ui.store.everything.EverythingFragment

class TabsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> BookStoreApplication.getAppResources().getString(R.string.everything_page_title)
            1 -> BookStoreApplication.getAppResources().getString(R.string.popular_page_title)
            2 -> BookStoreApplication.getAppResources().getString(R.string.novelties_page_title)
            else -> null
        }
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> EverythingFragment()
            1 -> PopularFragment()
            2 -> NoveltiesFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return 3
    }
}