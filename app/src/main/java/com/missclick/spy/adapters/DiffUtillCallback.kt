package com.missclick.spy.adapters

import androidx.recyclerview.widget.DiffUtil
import com.missclick.spy.data.models.CollectionsModel

class DiffUtilCallback(
        private val oldWordList : List<CollectionsModel>,
        private val newWordList : List<CollectionsModel>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldWordList[oldItemPosition] == newWordList[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldWordList.size
    }

    override fun getNewListSize(): Int {
        return newWordList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldWordList[oldItemPosition].name == newWordList[newItemPosition].name
    }
}