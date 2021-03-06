package com.alexsp0.nicefilmapp.ui.main

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alexsp0.nicefilmapp.MainActivity
import com.alexsp0.nicefilmapp.R
import com.alexsp0.nicefilmapp.REQUEST_CODE
import com.alexsp0.nicefilmapp.presenters.MainFilmsPresenter
import com.alexsp0.nicefilmapp.presenters.MainFilmsPresenterImpl
import com.alexsp0.nicefilmapp.utils.Film

class MainFilmsFragmentImpl(presenter: MainFilmsPresenter) : Fragment(), MainFilmsFragment  {
    private lateinit var presenter : MainFilmsPresenterImpl
    private lateinit var recyclerView1 : RecyclerView
    private lateinit var recyclerView2 : RecyclerView
    private lateinit var adapter1: MainFilmsFragmentAdapter
    private lateinit var adapter2: MainFilmsFragmentAdapter
    private var films : ArrayList<Film> = arrayListOf()
    private lateinit var progressBar : ProgressBar
    init {
        this.presenter = presenter as MainFilmsPresenterImpl
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_main_films, container, false)
        checkNecessaryPermissions()
        progressBar = view.findViewById(R.id.progrees_bar_load_films)
        initRecyclersView(view)
        presenter.getFilms()
        return view
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {

    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun initRecyclersView(view: View) {
        recyclerView1 = view.findViewById(R.id.recyclerview1)
        recyclerView1.hasFixedSize()
        adapter1 = MainFilmsFragmentAdapter(films)
        adapter1.setItemClickListener(object : MainFilmsFragmentAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                openCurrentFilmFragment(position)
            }
        })
        recyclerView1.adapter = adapter1
        //Second recyclerview
        recyclerView2 = view.findViewById(R.id.recyclerview2)
        recyclerView2.hasFixedSize()
        adapter2 = MainFilmsFragmentAdapter(films)
        adapter2.setItemClickListener(object : MainFilmsFragmentAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                openCurrentFilmFragment(position)
            }
        })
    }

    private fun openCurrentFilmFragment(position: Int) {
        val act = activity as MainActivity
        act.loadFragment(CurrentFilmFragment.newInstance(films[position], presenter))
    }

    companion object {
        @JvmStatic
        fun newInstance(presenter : MainFilmsPresenter) =  MainFilmsFragmentImpl(presenter)
    }

    override fun updateFilms(films : ArrayList<Film>) {
        this.films.clear()
        this.films.addAll(films)
        adapter1.notifyDataSetChanged()
    }

    override fun showProgressbar() {
        progressBar.visibility=ProgressBar.VISIBLE
    }

    override fun hideProgressbar() {
        progressBar.visibility=ProgressBar.INVISIBLE
    }

    private fun checkNecessaryPermissions() {
        val act = activity as MainActivity
        if(ContextCompat.checkSelfPermission(act,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) {
            getGeo()
        } else {

            AlertDialog.Builder(act).setTitle("???????????????????? ???? ????????????????????")
                .setMessage("?????????? ???????? ????????????????????, ?????????? ????????????????????, ???? ????????????")
                .setPositiveButton("????????????????!") { _, _ -> requestNecessaryPermission()}
                .setNegativeButton("?? ??????????????????!") { dialog, _ -> dialog.dismiss() }
                .setNeutralButton("???????? ?? ?????????????????????") { dialog: DialogInterface, which ->
                    val intent = Intent()
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent.setData(Uri.parse("package:" + act.packageName))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                    act.startActivity(intent)

                }
                .create().show()
            requestNecessaryPermission()
        }
    }

    private fun requestNecessaryPermission() {
        val act = activity as MainActivity
        ActivityCompat.requestPermissions(act,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
    }

    private fun getGeo() {

    }

}