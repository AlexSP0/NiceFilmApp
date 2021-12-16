package com.alexsp0.nicefilmapp


import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.alexsp0.nicefilmapp.presenters.MainFilmsPresenter
import com.alexsp0.nicefilmapp.presenters.MainFilmsPresenterImpl
import com.alexsp0.nicefilmapp.ui.main.MainFilmsFragmentImpl
import com.alexsp0.nicefilmapp.ui.main.SettingsFragment
import com.alexsp0.nicefilmapp.utils.InetBroadcastReceiver
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.core.app.ActivityCompat.requestPermissions

const val REQUEST_CODE = 111

//Функция-расширение как выражение и как фича котлина
fun MainActivity.showSnackbarWithText(res : Int) =
    Snackbar.make(this.findViewById(android.R.id.content),
        this.applicationContext.resources.getText(res),
        Snackbar.LENGTH_SHORT).show()

class MainActivity : AppCompatActivity() {
    private var presenter : MainFilmsPresenter = MainFilmsPresenterImpl(this)
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigationView()
        loadFragment(MainFilmsFragmentImpl.newInstance(presenter))
        registerReceiver(InetBroadcastReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    private fun initNavigationView() {
        val navigationView : BottomNavigationView = this.findViewById(R.id.navigationView)
        navigationView.itemIconTintList=null
        navigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_menu_home -> {
                    loadFragment(MainFilmsFragmentImpl.newInstance(presenter))
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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_main_search -> {
                loadFragment(SettingsFragment.newInstance(presenter))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun loadFragment(fragment : Fragment) {
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


