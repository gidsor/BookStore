package com.gidsor.bookstore.ui.main

import android.content.res.Resources
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.res.TypedArrayUtils.getResourceId
import android.support.v4.content.res.TypedArrayUtils.getString
import com.gidsor.bookstore.BookStoreApplication
import com.gidsor.bookstore.R
import com.gidsor.bookstore.ui.main.novelties.NoveltiesFragment
import com.gidsor.bookstore.ui.main.popular.PopularFragment
import com.gidsor.bookstore.ui.main.store.StoreFragment

class TabsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> BookStoreApplication.getAppResources().getString(R.string.store_page_title)
            1 -> BookStoreApplication.getAppResources().getString(R.string.popular_page_title)
            2 -> BookStoreApplication.getAppResources().getString(R.string.novelties_page_title)
            else -> null
        }
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> StoreFragment()
            1 -> PopularFragment()
            2 -> NoveltiesFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return 3
    }
}