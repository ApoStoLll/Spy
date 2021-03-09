package com.missclick.spy.ui.menu

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.missclick.spy.R
import com.missclick.spy.data.models.GameParams
import com.missclick.spy.databinding.FragmentMenuBinding
import com.missclick.spy.ui.cards.CardsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment(R.layout.fragment_menu) {
    private val viewModel : MenuViewModel by viewModel()
    private val binding by viewBinding(FragmentMenuBinding::bind)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var players = 4
        var spy = 1
        var time = 3
        var set = "базовый"
        updatePlayers(players)
        binding.textSpy.text = spy.toString()
        binding.textTimer.text = time.toString() + " " + getString(R.string.min)
        binding.textSet.text = set

        binding.imagePlayersLeft.setOnClickListener {
            if (players > 1 )
                players -= 1
//            if (players == 1)
                //todo change color of the image
            updatePlayers(players)
        }
        binding.imagePlayersRight.setOnClickListener {
//            if (players == 1) //todo change color of the image
            players += 1
            updatePlayers(players)
        }

        val param = GameParams(
            players = players,
            spy = spy,
            time = time,
            category = set
        )
        binding.buttonStart.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_cardsFragment, CardsFragment.newInstance(params = param))
        }
    }

    private fun updatePlayers(players : Int){
        binding.textPlayers.text = players.toString()
    }

}