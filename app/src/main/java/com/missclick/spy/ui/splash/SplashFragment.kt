package com.missclick.spy.ui.splash

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.missclick.spy.R
import com.missclick.spy.data.models.WordsModel
import com.missclick.spy.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment(R.layout.fragment_splash){
    private val binding by viewBinding(FragmentSplashBinding::bind)
    private val viewModel : SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.firstLaunch.asLiveData().observe(viewLifecycleOwner){
            if(it)
                viewModel.preloadDb(getWordsFromStringArray())
            else {
                Log.e("splash", "go")
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }
        }
        viewModel.ids.observe(viewLifecycleOwner){
            viewModel.setFirstLaunch(false)
            findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
        }

    }

    private fun getWordsFromStringArray() : List<WordsModel>{
        val words = resources.getStringArray(R.array.Basic)
        return words.map {
            WordsModel(word = it, category = getString(R.string.Basic))
        }
    }
}