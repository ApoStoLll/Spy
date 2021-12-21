package com.missclick.spy.ui.settings

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.transition.MaterialFadeThrough
import com.missclick.spy.MainActivity
import com.missclick.spy.R
import com.missclick.spy.data.local.SettingsRepository
import com.missclick.spy.databinding.FragmentSettingsBinding
import com.missclick.spy.ui.menu.MenuViewModel
import com.missclick.spy.utills.LocalLanguage
import com.missclick.spy.utills.SetsManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private val binding by viewBinding(FragmentSettingsBinding::bind)
    private val viewModel : SettingsViewModel by viewModel()
    private val setsManager : SetsManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.language.asLiveData().observe(viewLifecycleOwner){
            updateColor(LocalLanguage.mapStringToLang(it))
            Log.e("upd",it)
        }

        viewModel.initManager.observe(viewLifecycleOwner){
            if(it){
                setsManager.initSets(resources)
                Log.e("Init","sets")
                viewModel.preloadDb(setsManager.getWords())
            }
        }

        //todo instead of activity as MainActivity - settingsRepository
        binding.cardViewEng.setOnClickListener {
            LocalLanguage.changeLocale(resources, LocalLanguage.English)
            //resources.configuration.setLocale(Locale(LocalLanguage.mapLangToString(LocalLanguage.English)))
            viewModel.setLanguage(LocalLanguage.English, getString(R.string.basic))
        }

        binding.cardViewRus.setOnClickListener {
            LocalLanguage.changeLocale(resources, LocalLanguage.Russian)
            viewModel.setLanguage(LocalLanguage.Russian, getString(R.string.basic))
        }

        binding.cardViewUkr.setOnClickListener {
            LocalLanguage.changeLocale(resources, LocalLanguage.Ukrainian)
            viewModel.setLanguage(LocalLanguage.Ukrainian, getString(R.string.basic))
        }

        binding.appCompatImageButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.appCompatImageButton1.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun updateColor(lang : LocalLanguage){
        val white = R.color.white
        binding.textRus.setTextColor(resources.getColor(white))
        binding.textEng.setTextColor(resources.getColor(white))
        binding.textUkr.setTextColor(resources.getColor(white))
        val orange = R.color.orange
        when(lang){
            is LocalLanguage.English -> binding.textEng.setTextColor(resources.getColor(orange))
            is LocalLanguage.Russian -> binding.textRus.setTextColor(resources.getColor(orange))
            is LocalLanguage.Ukrainian -> binding.textUkr.setTextColor(resources.getColor(orange))
        }
//        if (lang == "rus") binding.textRus.setTextColor(resources.getColor(orange))
//        if (lang == "eng") binding.textEng.setTextColor(resources.getColor(orange))
//        if (lang == "ukr") binding.textUkr.setTextColor(resources.getColor(orange))
    }

}