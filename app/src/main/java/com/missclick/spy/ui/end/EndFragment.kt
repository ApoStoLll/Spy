package com.missclick.spy.ui.end

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.missclick.spy.R
import com.missclick.spy.databinding.FragmentEndBinding


private const val PARAMS_ARG = "spy"
class EndFragment : Fragment(R.layout.fragment_end) {
    private val binding by viewBinding(FragmentEndBinding::bind)

    var spies : ArrayList<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            spies = it.getIntegerArrayList(PARAMS_ARG)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (spies!!.size == 1)
            binding.textPlayer.text = "${binding.textPlayer} ${spies!![0]}"
        else{
            var spy = ""
            for (i in spies!!){
                spy += "$i,"
            }
            spy = spy.dropLast(1)
            binding.textWasSpy.text = resources.getString(R.string.were_spies)
            binding.textPlayer.text = "${resources.getString(R.string.players)} $spy"
        }

        binding.buttonRestart.setOnClickListener {
            findNavController().navigate(R.id.action_endFragment_to_menuFragment)
        }
    }

    companion object{
        fun newInstance(spy: ArrayList<Int>) =
                Bundle().apply {
                    putIntegerArrayList(PARAMS_ARG, spy)
                }
    }
}