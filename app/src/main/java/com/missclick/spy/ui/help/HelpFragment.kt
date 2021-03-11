package com.missclick.spy.ui.help

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.missclick.spy.R
import com.missclick.spy.databinding.FragmentHelpBinding

class HelpFragment : Fragment(R.layout.fragment_help) {
    val binding by viewBinding(FragmentHelpBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appCompatImageButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.appCompatImageButton1.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}