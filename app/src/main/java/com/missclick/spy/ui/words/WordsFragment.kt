package com.missclick.spy.ui.words

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.missclick.spy.R
import com.missclick.spy.adapters.CollectionsListAdapter
import com.missclick.spy.adapters.WordsListAdapter
import com.missclick.spy.data.models.CollectionsModel
import com.missclick.spy.data.models.WordListModel
import com.missclick.spy.databinding.FragmentWordsBinding
import com.missclick.spy.ui.sets.SetsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

const val SET = "name"
class WordsFragment : Fragment(R.layout.fragment_words) {
    val binding by viewBinding(FragmentWordsBinding::bind)
    private val viewModel : WordsViewModel by viewModel()

    var setName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            setName = getString(SET)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textSetName.text = setName
        viewModel.getWords(setName!!).observe(viewLifecycleOwner){ it ->
            //val mutableItems = mutableListOf<String>().apply { addAll(it) }
            val words = it.map {
                WordListModel(word = it)
            }.toMutableList()
            binding.recycleWords.adapter = WordsListAdapter(words){

            }
            binding.recycleWords.layoutManager = LinearLayoutManager(requireActivity())
        }
        binding.appCompatImageButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.appCompatImageButton1.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    companion object{
        fun newInstance(setName : String) =
                Bundle().apply {
                    putString(SET,setName)
                }
    }
}