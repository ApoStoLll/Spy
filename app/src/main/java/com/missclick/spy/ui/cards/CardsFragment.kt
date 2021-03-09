package com.missclick.spy.ui.cards

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import by.kirich1409.viewbindingdelegate.viewBinding
import com.missclick.spy.R
import com.missclick.spy.data.models.GameParams
import com.missclick.spy.databinding.FragmentCardsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

const val PARAMS_ARG = "params"

class CardsFragment : Fragment(R.layout.fragment_cards) {
    private val viewModel : CardsViewModel by viewModel()
    private val binding by viewBinding(FragmentCardsBinding::bind)

    private var params : GameParams? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getSerializable(PARAMS_ARG) as GameParams
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.getRandomWord(params!!.category).observe(viewLifecycleOwner){
//            val spy = viewModel.getSpy(params!!.players)
//
//        }

    }


    companion object{
        fun newInstance(params: GameParams) =
                CardsFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(PARAMS_ARG, params)
                    }
                }
    }
}