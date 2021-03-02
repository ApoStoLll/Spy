package com.missclick.spy.ui.viewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.missclick.spy.R
import com.missclick.spy.databinding.FragmentGuideBinding

private const val ARG_PARAM1 = "param1"

class GuideFragment : Fragment(R.layout.fragment_guide){
    private val binding by viewBinding(FragmentGuideBinding::bind)

    private var param1: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tv.text = param1.toString()
    }

    companion object{
        fun newInstance(param1 : Int) =
            Bundle().apply {
                putInt(ARG_PARAM1, param1)
            }
    }
}