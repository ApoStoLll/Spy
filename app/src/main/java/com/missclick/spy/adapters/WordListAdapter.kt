package com.missclick.spy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.missclick.spy.R
import com.missclick.spy.data.models.WordListModel

class WordListAdapter (
        private val items : MutableList<WordListModel>,
        private val onClickListener : (WordListModel) -> Unit
) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder =
            WordViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.word_list_item, parent, false))

    override fun getItemCount(): Int = items.size

    fun updateWordListItems(newItems : List<WordListModel>){
        val diffUtilCallback = DiffUtilCallback(oldWordList = items, newWordList = newItems)
        val diffRes = DiffUtil.calculateDiff(diffUtilCallback)
        items.clear()
        items.addAll(newItems)
        diffRes.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class WordViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bind(item : WordListModel){

        }
    }
}