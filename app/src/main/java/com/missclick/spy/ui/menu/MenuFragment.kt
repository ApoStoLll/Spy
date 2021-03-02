package com.missclick.spy.ui.menu

import androidx.fragment.app.Fragment
import com.missclick.spy.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment(R.layout.fragment_menu) {
    private val viewModel : MenuViewModel by viewModel()

}