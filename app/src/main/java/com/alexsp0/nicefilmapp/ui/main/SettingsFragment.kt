package com.alexsp0.nicefilmapp.ui.main

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.annotation.RequiresApi
import com.alexsp0.nicefilmapp.R
import com.alexsp0.nicefilmapp.presenters.MainFilmsPresenter

class SettingsFragment(presenter : MainFilmsPresenter) : Fragment() {
    private lateinit var okButton : Button
    private lateinit var isAdultCheckbox : CheckBox
    private lateinit var presenter : MainFilmsPresenter
    init {
        this.presenter = presenter
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_settings, container, false)
        isAdultCheckbox = view.findViewById(R.id.show_adult_checkBox)
        isAdultCheckbox.isChecked = presenter.getAdultSettings()
        okButton = view.findViewById(R.id.setting_fragment_ok_button)
        okButton.setOnClickListener {
            presenter.setAdultSettings(isAdultCheckbox.isChecked)
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        return view
    }

    companion object {
        @RequiresApi(Build.VERSION_CODES.N)
        @JvmStatic
        fun newInstance(presenter: MainFilmsPresenter) = SettingsFragment(presenter)
    }
}