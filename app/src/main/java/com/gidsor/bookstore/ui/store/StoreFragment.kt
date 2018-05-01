package com.gidsor.bookstore.ui.store

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gidsor.bookstore.R

class StoreFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: TabsPagerAdapter
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setupViewPager()
        return inflater.inflate(R.layout.fragment_everything, container, false)
    }

    private fun setupViewPager() {
//        viewPager = findViewById(R.id.view_pager)
//        pagerAdapter = TabsPagerAdapter(supportFragmentManager)
//        viewPager.adapter = pagerAdapter
//
//        tabLayout = findViewById(R.id.tab_layout)
//        tabLayout.setupWithViewPager(viewPager)
    }
}