package com.missclick.spy.ui.timer

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.missclick.spy.R
import com.missclick.spy.data.models.GameParams
import com.missclick.spy.databinding.FragmentTimerBinding
import com.missclick.spy.ui.cards.CardsFragment
import com.missclick.spy.ui.end.EndFragment

private const val PARAMS_ARG = "params"

class TimerFragment : Fragment(R.layout.fragment_timer){

    private val binding by viewBinding(FragmentTimerBinding::bind)

    var timer : CountDownTimer? = null

    private var time : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            time = it.getInt(PARAMS_ARG)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timer = object : CountDownTimer(time?.toLong()!!, 1000){
            override fun onFinish() {
                //todo string to values
                binding.timerText.text = "Time end"

            }

            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                val sec = millisUntilFinished % 60000 / 1000
                val t =
                    if(sec < 10) "${millisUntilFinished / 1000 / 60}:0${sec}"
                    else "${millisUntilFinished / 1000 / 60}:${sec}"
                binding.timerText.text = t
            }
        }
        (timer as CountDownTimer).start()
        binding.buttonShowSpy.setOnClickListener {
            navigate(arrayListOf(2,3))
        }
    }

    private fun navigate(spies : ArrayList<Int>){
        timer?.cancel()
        findNavController().navigate(R.id.action_timerFragment_to_endFragment,EndFragment.newInstance(spy = spies))
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }

    companion object{
        fun newInstance(time: Int) =
            Bundle().apply {
                putInt(PARAMS_ARG, time)
            }
    }
}

