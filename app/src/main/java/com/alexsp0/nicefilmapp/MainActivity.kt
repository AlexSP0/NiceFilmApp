package com.alexsp0.nicefilmapp


import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alexsp0.nicefilmapp.ui.main.MainFilmsFragmentImpl
import com.alexsp0.nicefilmapp.utils.InetBroadcastReceiver
import com.alexsp0.nicefilmapp.utils.MyLog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

//Функция-расширение как выражение и как фича котлина
fun MainActivity.showSnackbarWithText(res : Int) =
    Snackbar.make(this.findViewById(android.R.id.content),
        this.applicationContext.resources.getText(res),
        Snackbar.LENGTH_SHORT).show()

class MainActivity : AppCompatActivity() {
    private lateinit var log : MyLog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log = MyLog(this)
        log.logEvent("Activity", "Start activity")
        setContentView(R.layout.activity_main)
        loadFragment(MainFilmsFragmentImpl.newInstance())
        initNavigationView()
        registerReceiver(InetBroadcastReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        log.logEvent("Activity", "Start activity")
    }

    private fun initNavigationView() {
        val navigationView : BottomNavigationView = this.findViewById(R.id.navigationView)
        navigationView.itemIconTintList=null
        navigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_menu_home -> {
                    loadFragment(MainFilmsFragmentImpl.newInstance())
                }
                R.id.navigation_menu_back -> {
                    showWarning()
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
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun loadFragment(fragment : Fragment) {
        log.logEvent("Activity", "loading fragment\n")
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).addToBackStack(fragment.toString()).commit()
    }
    private fun loadPreviousFragment() {
        if(supportFragmentManager.backStackEntryCount>1) {
            supportFragmentManager.popBackStack()
        }
    }
    private fun showWarning() {
        this.showSnackbarWithText(R.string.snackbar_text)
    }
}