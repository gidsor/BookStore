package com.gidsor.bookstore.ui.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.gidsor.bookstore.*
import com.gidsor.bookstore.ui.main.novelties.NoveltiesFragment
import com.gidsor.bookstore.ui.main.popular.PopularFragment
import com.gidsor.bookstore.ui.main.store.StoreFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: FixedTabsPagerAdapter
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewPager()
    }

    private fun setupViewPager() {
        viewPager = findViewById(R.id.view_pager)
        pagerAdapter = FixedTabsPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter

        tabLayout = findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }
}
