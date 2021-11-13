package com.alexsp0.nicefilmapp


import android.os.Bundle

import androidx.*
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alexsp0.nicefilmapp.R.id.navigation_menu_home
import com.alexsp0.nicefilmapp.ui.main.MainFilmsFragmentImpl
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var fragmentManager : FragmentManager = supportFragmentManager
        var fragment : Fragment
        fragment=MainFilmsFragmentImpl.newInstance()
        loadFragment(fragment)
        initNavigationView();

    }

    private fun initNavigationView() {
        var navigationView : BottomNavigationView = this.findViewById(R.id.navigationView)
        navigationView.itemIconTintList=null
        navigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_menu_home -> {
                    loadFragment(MainFilmsFragmentImpl.newInstance())
                }
                R.id.navigation_menu_back -> {
                    loadPreviousFragment()
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_main_search -> {
                return true;
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun loadFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).addToBackStack(fragment.toString()).commit()
    }
    fun loadPreviousFragment() {
        if(supportFragmentManager.backStackEntryCount>1) {
            supportFragmentManager.popBackStack()
        }
    }
}