package com.missclick.spy.ui.sets

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import by.kirich1409.viewbindingdelegate.viewBinding
import com.missclick.spy.R
import com.missclick.spy.databinding.FragmentSetsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SetsFragment : Fragment(R.layout.fragment_sets) {
    private val binding by viewBinding(FragmentSetsBinding::bind)
    private val viewModel : SetsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSets().observe(viewLifecycleOwner){

        }
    }
}