package com.missclick.spy.ui.words

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
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
import android.util.Log


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
        val adapter =  WordsListAdapter()
        viewModel.getWords(setName!!).observe(viewLifecycleOwner){ it ->
            //val mutableItems = mutableListOf<String>().apply { addAll(it) }
            var words = it.map {
                WordListModel(word = it)
            }.toMutableList()

            adapter.setData(words)
            adapter.setOnClickListener {
                if(it.editable){
                    Log.e("word",words.toString())
                    val word = WordListModel(word = words.last().word)
                    words.remove(words.last())
                    words.add(word)
                    adapter.updateWordListItems(words)
                    Log.e("word",words.toString())
                }
                else{
                    words.remove(it)
                    adapter.updateWordListItems(words)
                }
            }
            binding.recycleWords.adapter = adapter
            binding.recycleWords.layoutManager = LinearLayoutManager(requireActivity())
            binding.imageGarbage.setOnClickListener {
                words.add(WordListModel("",true))
                adapter.updateWordListItems(words)
                val a = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                a.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
            }
        }
        binding.appCompatImageButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.appCompatImageButton1.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.buttonChoose.setOnClickListener {
            val data = adapter.getList()
            val oldName = setName!!
            val newName = binding.textSetName.text.toString()
            viewModel.update(oldSetName = oldName,newSetName = newName,data = data)
            findNavController().navigate(R.id.action_wordsFragment_to_menuFragment)
        }
        binding.imagePen.setOnClickListener {
            binding.textSetName.isEnabled = true
            binding.textSetName.requestFocus()
            binding.textSetName.setSelection(binding.textSetName.length())
            val a = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            a.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
//            (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).
//            hideSoftInputFromWindow(view.windowToken, 0)

        }


    }

    override fun onPause() {
        super.onPause()
        val a = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        a.hideSoftInputFromWindow(view?.windowToken, 0)
    }


    companion object{
        fun newInstance(setName: String) =
                Bundle().apply {
                    putString(SET, setName)
                }
    }
}