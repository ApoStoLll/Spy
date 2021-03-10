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


    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var players = 4
        var spies = 1
        var time = 3
        var set = "Basic"
        updatePlayers(players)
        updateSpies(spies)
        updateTime(time)
        binding.textSet.text = set

        binding.imagePlayersLeft.setOnClickListener {
            if (players > 1 )
                players -= 1
//            if (players == 1)
//                binding.imagePlayersLeft.setStrokeColorResource(resources.getColor(R.color.orange2))
            updatePlayers(players)
        }
        binding.imagePlayersRight.setOnClickListener {
//            if (players == 1) //todo change color of the image
            players += 1
            updatePlayers(players)
        }

        binding.imageSpiesLeft.setOnClickListener {
            if (spies > 1 )
                spies -= 1
//            if (players == 1)
            //todo change color of the image
            updateSpies(spies)
        }
        binding.imageSpiesRight.setOnClickListener {
//            if (spies == 1) //todo change color of the image
            spies += 1
            updateSpies(spies)
        }

        binding.imageTimeLeft.setOnClickListener {
            if (time > 1 )
                time -= 1
//            if (time == 1)
            //todo change color of the image
            updateTime(time)
        }
        binding.imageTimeRight.setOnClickListener {
//            if (time == 1) //todo change color of the image
            time += 1
            updateTime(time)
        }

        val param = GameParams(
            players = players,
            spy = spies,
            time = time * 1000 * 60,
            category = set
        )
        binding.buttonStart.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_cardsFragment, CardsFragment.newInstance(params = param))
        }
        binding.imageHelp.setOnClickListener {
            findNavController().navigate(R.id.action_cardsFragment_to_helpFragment)
        }
        binding.imageSettings.setOnClickListener {
            findNavController().navigate(R.id.action_cardsFragment_to_settingsFragment)
        }
    }

    private fun updatePlayers(players : Int){
        binding.textPlayers.text = players.toString()
    }

    private fun updateSpies(spies : Int){
        binding.textSpy.text = spies.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun updateTime(time : Int){
        binding.textTimer.text = time.toString() + " " + getString(R.string.min)
    }


}