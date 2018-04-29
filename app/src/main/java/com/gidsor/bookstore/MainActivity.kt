package com.gidsor.bookstore

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sectionsPageAdapter: SectionsPageAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sectionsPageAdapter = SectionsPageAdapter(supportFragmentManager)

        viewPager = findViewById(R.id.container)
        setupViewPager(viewPager)

        val tabLayout: TabLayout = findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = SectionsPageAdapter(supportFragmentManager)
        adapter.addFragment(StoreFragment(), "Книги")
        adapter.addFragment(GenresFragment(), "Жанры")
        adapter.addFragment(PopularFragment(), "Популярное")
        adapter.addFragment(NoveltiesFragment(), "Новинки")
        viewPager.adapter = adapter
    }
}
