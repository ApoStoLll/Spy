package com.missclick.spy.ui.cards

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.missclick.spy.R
import com.missclick.spy.data.models.GameParams
import com.missclick.spy.databinding.FragmentCardsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val PARAMS_ARG = "params"

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
        binding.appCompatImageButton.setOnClickListener {
            findNavController().navigateUp()
        }

        if (params != null)
            viewModel.getRandomWord(params!!.category).observe(viewLifecycleOwner){role ->
                val spy = viewModel.getSpy(params!!.players)
                viewModel.cardState.observe(viewLifecycleOwner){
                    when(it){
                        is CardState.ClosedCard ->
                            binding.apply {
                                roleImage.visibility = View.GONE
                                nameRole.text = it.number.toString()
                                descriptionRole.text = getString(R.string.click_to_see_you_role)
                            }
                        is CardState.OpenedCard -> {
                            if (it.number == spy)
                                binding.apply {
                                    roleImage.setImageResource(R.drawable.ic_spy_hat)
                                    nameRole.text = getString(R.string.you_spy)
                                    descriptionRole.text = getString(R.string.you_spy_hint)
                                }
                            else
                                binding.apply {
                                    roleImage.setImageResource(R.drawable.ic_member_location)
                                    nameRole.text = role
                                    descriptionRole.text = getString(R.string.you_member)
                                }
                            binding.roleImage.visibility = View.VISIBLE

                        }
                    }
                }
                binding.cardView.setOnClickListener{
                    viewModel.changeState()
                }
            }
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