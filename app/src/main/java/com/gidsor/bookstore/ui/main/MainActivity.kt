package com.gidsor.bookstore.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.gidsor.bookstore.*
import com.gidsor.bookstore.ui.account.AccountFragment
import com.gidsor.bookstore.ui.genre.GenreFragment
import com.gidsor.bookstore.ui.purchase.PurchaseFragment
import com.gidsor.bookstore.ui.store.StoreFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(StoreFragment())

        val navigationView: BottomNavigationView = findViewById(R.id.navigation)
        navigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_store -> loadFragment(StoreFragment())
            R.id.navigation_genre -> loadFragment(GenreFragment())
            R.id.navigation_purchase -> loadFragment(PurchaseFragment())
            R.id.navigation_account -> loadFragment(AccountFragment())
            else -> loadFragment(StoreFragment())
        }
    }

    private fun loadFragment(fragment: Fragment?) : Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
            return true
        }
        return false
    }
}
