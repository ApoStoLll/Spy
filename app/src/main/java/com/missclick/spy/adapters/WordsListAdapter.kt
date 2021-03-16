package com.missclick.spy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.missclick.spy.R
import com.missclick.spy.adapters.diff.DiffUtilWordsCallback
import com.missclick.spy.data.models.CollectionsModel
import com.missclick.spy.data.models.WordListModel
import com.missclick.spy.databinding.CollectionsListItemBinding
import com.missclick.spy.databinding.WordsListItemsBinding

class WordsListAdapter (
        private val items : MutableList<WordListModel>,
        private val onClickListener : (CollectionsModel) -> Unit
) : RecyclerView.Adapter<WordsListAdapter.WordsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder =
            WordsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.words_list_items, parent, false))

    override fun getItemCount(): Int = items.size

    fun updateWordListItems(newItems : List<WordListModel>){
        val diffUtilCallback = DiffUtilWordsCallback(oldWordList = items, newWordList = newItems)
        val diffRes = DiffUtil.calculateDiff(diffUtilCallback)
        items.clear()
        items.addAll(newItems)
        diffRes.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class WordsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val binding by viewBinding(WordsListItemsBinding::bind)
        fun bind(item : WordListModel){
            binding.textWord.text = item.word
        }
    }
}