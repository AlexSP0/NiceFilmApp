package com.alexsp0.nicefilmapp


import android.os.Bundle

import androidx.*
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alexsp0.nicefilmapp.ui.main.MainFilmsFragmentImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var fragmentManager : FragmentManager = supportFragmentManager
        var fragment : Fragment
        fragment=MainFilmsFragmentImpl.newInstance()
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()

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
}