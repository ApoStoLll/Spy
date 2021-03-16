package com.missclick.spy.ui.words

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.missclick.spy.R
import com.missclick.spy.adapters.WordsListAdapter
import com.missclick.spy.data.models.WordListModel
import com.missclick.spy.databinding.FragmentWordsBinding
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
        binding.textSetName.setText(setName)
        val adapter =  WordsListAdapter(){

        }
        viewModel.getWords(setName!!).observe(viewLifecycleOwner){ it ->
            //val mutableItems = mutableListOf<String>().apply { addAll(it) }
            val words = it.map {
                WordListModel(word = it)
            }.toMutableList()
            adapter.setData(words)
            binding.recycleWords.adapter = adapter
            binding.recycleWords.layoutManager = LinearLayoutManager(requireActivity())
        }
        binding.appCompatImageButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.appCompatImageButton1.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.materialButton.setOnClickListener {
            val data = adapter.getList()
        }
        binding.imagePen.setOnClickListener {
            binding.textSetName.isEnabled = true
            binding.textSetName.requestFocus()
            binding.textSetName.setSelection(binding.textSetName.length())
//            (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).
//            hideSoftInputFromWindow(view.windowToken, 0)

        }
    }

    companion object{
        fun newInstance(setName: String) =
                Bundle().apply {
                    putString(SET, setName)
                }
    }
}