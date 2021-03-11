package com.missclick.spy.ui.settings

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.missclick.spy.MainActivity
import com.missclick.spy.R
import com.missclick.spy.data.local.SettingsRepository
import com.missclick.spy.databinding.FragmentSettingsBinding
import org.koin.android.ext.android.inject

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private val binding by viewBinding(FragmentSettingsBinding::bind)
    private val settingsRepository : SettingsRepository by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeColor((activity as MainActivity).language,true)
        //todo instead of activity as MainActivity - settingsRepository
        binding.cardViewEng.setOnClickListener {
            changeColor((activity as MainActivity).language,false)
            (activity as MainActivity).language = "eng"
            changeColor((activity as MainActivity).language,true)
        }

        binding.cardViewRus.setOnClickListener {
            changeColor((activity as MainActivity).language,false)
            (activity as MainActivity).language = "rus"
            changeColor((activity as MainActivity).language,true)
        }

        binding.cardViewUkr.setOnClickListener {
            changeColor((activity as MainActivity).language,false)
            (activity as MainActivity).language = "ukr"
            changeColor((activity as MainActivity).language,true)
        }

        binding.appCompatImageButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.appCompatImageButton1.setOnClickListener {
            findNavController().navigateUp()
        }

    }


    private fun changeColor(lang : String, orange : Boolean){
        var color = R.color.white
        if(orange) color = R.color.orange
        if (lang == "rus") binding.textRus.setTextColor(resources.getColor(color))
        if (lang == "eng") binding.textEng.setTextColor(resources.getColor(color))
        if (lang == "ukr") binding.textUkr.setTextColor(resources.getColor(color))
    }
}