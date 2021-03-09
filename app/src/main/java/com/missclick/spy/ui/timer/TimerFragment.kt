package com.missclick.spy.ui.timer

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.missclick.spy.R
import com.missclick.spy.data.models.GameParams
import com.missclick.spy.databinding.FragmentTimerBinding
import com.missclick.spy.ui.cards.CardsFragment

private const val PARAMS_ARG = "params"

class TimerFragment(countDownInterval: Long) : Fragment(R.layout.fragment_timer){

    private val binding by viewBinding(FragmentTimerBinding::bind)

    private var time : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            time = it.getInt(PARAMS_ARG)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val timer = object : CountDownTimer(time?.toLong()!!, 1000){
            override fun onFinish() {
                TODO("Not yet implemented")
            }

            override fun onTick(millisUntilFinished: Long) {
                TODO("Not yet implemented")
            }

        }
    }

    companion object{
        fun newInstance(time: Int) =
            CardsFragment().apply {
                arguments = Bundle().apply {
                    putInt(PARAMS_ARG, time)
                }
            }
    }
}