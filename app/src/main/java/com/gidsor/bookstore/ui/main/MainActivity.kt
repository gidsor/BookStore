package com.gidsor.bookstore.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.gidsor.bookstore.*
import com.gidsor.bookstore.data.model.Book
import com.gidsor.bookstore.ui.account.AccountFragment
import com.gidsor.bookstore.ui.genre.GenreFragment
import com.gidsor.bookstore.ui.basket.PurchaseFragment
import com.gidsor.bookstore.ui.store.StoreFragment
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.gidsor.bookstore.ui.companyinfo.AboutFragment
import com.gidsor.bookstore.ui.companyinfo.ConfidentialityFragment
import com.gidsor.bookstore.ui.companyinfo.ContactsFragment
import com.gidsor.bookstore.ui.companyinfo.DeliveryFragment
import com.gidsor.bookstore.ui.basket.ServiceAndPaymentFragment
import com.gidsor.bookstore.ui.companyinfo.ReferenceFragment
import com.gidsor.bookstore.ui.store.BookItemFragment
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.gidsor.bookstore.data.database.BasketArrayData


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var searchView: MaterialSearchView
    private lateinit var drawer: Drawer

    lateinit var badge: TextView

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(StoreFragment())

        // Add search
        searchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    loadStoreFragmentWithGenreAndSearch("", newText)
                }
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                loadStoreFragmentWithGenreAndSearch("", query)
                (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(currentFocus.windowToken, 0)
                return true
            }
        })

        // Add bottom navigation
        bottomNavigationView = findViewById(R.id.navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        // Always show text in navigation for more 3 elements and disable shift mode
        bottomNavigationView.disableShiftMode()
        bottomNavigationView.menu.getItem(0).isChecked = true

        val bottomNavigationMenuView = bottomNavigationView.getChildAt(0) as BottomNavigationMenuView
        val bottomNavigationMenuItem = bottomNavigationMenuView.getChildAt(2)
        val itemView = bottomNavigationMenuItem as BottomNavigationItemView

        // Add badge notification. Count books in basket
        val badgeView = LayoutInflater.from(this)
                .inflate(R.layout.notification_badge, bottomNavigationMenuView, false)
        itemView.addView(badgeView)
        badge = badgeView.findViewById(R.id.badge)
        badge.text = BasketArrayData.countOfBooks().toString()


        // Add drawer
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawer = buildDrawer()
    }

    fun loadStoreFragmentWithGenreAndSearch(genre: String = "", search: String = "") {
        val storeFragment = StoreFragment()
        storeFragment.genreToShow = genre
        storeFragment.searchTitle = search
        bottomNavigationView.menu.getItem(0).isChecked = true
        loadFragment(storeFragment)
    }

    fun loadServiceAndPaymentFragment() {
        val paymentFragment = ServiceAndPaymentFragment()
        loadFragment(paymentFragment)
    }

    fun loadBookItemFragment(book: Book) {
        val bookItemFragment = BookItemFragment()
        bookItemFragment.bookToShow = book
        loadFragment(bookItemFragment)
    }

    @SuppressLint("RestrictedApi")
    private fun BottomNavigationView.disableShiftMode() {
        val menuView = getChildAt(0) as BottomNavigationMenuView
        val shiftingMode = menuView::class.java.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false
        for (i in 0 until menuView.childCount) {
            val item = menuView.getChildAt(i) as BottomNavigationItemView
            item.setShiftingMode(false)
            // Set once again checked value, so view will be updated
            item.setChecked(item.itemData.isChecked)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer.closeDrawer()
        drawer.setSelection(-1)
        bottomNavigationView.menu.setGroupCheckable(0, true, true)
        searchView.closeSearch()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val item: MenuItem = menu!!.findItem(R.id.action_search)
        searchView.setMenuItem(item)
        return true
    }

    override fun onBackPressed() {
        if (searchView.isSearchOpen) {
            searchView.closeSearch()
        } else {
            super.onBackPressed()
        }
    }

    private fun buildDrawer(): Drawer {
        return DrawerBuilder()
                .withActivity(this)
                .withRootView(R.id.drawer_container)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        PrimaryDrawerItem().withName("Публичная оферта")
                                .withIcon(R.drawable.ic_reference_outline_white_24dp),
                        PrimaryDrawerItem().withName("Конфиденциальность")
                                .withIcon(R.drawable.ic_confidentiality_white_24dp),
                        PrimaryDrawerItem().withName("Доставка и оплата")
                                .withIcon(R.drawable.ic_payment_white_24dp),
                        PrimaryDrawerItem().withName("Контакты")
                                .withIcon(R.drawable.ic_contact_support_white_24dp),
                        PrimaryDrawerItem().withName("О компании")
                                .withIcon(R.drawable.ic_about_white_24dp)
                )
                .withOnDrawerItemClickListener {view, position, drawerItem ->
                    bottomNavigationView.menu.setGroupCheckable(0, false, true)
                    when (position) {
                        0 -> !loadFragment(ReferenceFragment())
                        1 -> !loadFragment(ConfidentialityFragment())
                        2 -> !loadFragment(DeliveryFragment())
                        3 -> !loadFragment(ContactsFragment())
                        4 -> !loadFragment(AboutFragment())
                        else -> true
                    }
                }
                .withSelectedItem(-1)
                .build()
    }
}
