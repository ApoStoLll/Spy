package com.missclick.spy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.missclick.spy.R
import com.missclick.spy.data.models.CollectionsModel
import com.missclick.spy.databinding.CollectionsListItemBinding

class CollectionsListAdapter (
        private val items : MutableList<CollectionsModel>,
        private val onClickListener : (CollectionsModel) -> Unit
) : RecyclerView.Adapter<CollectionsListAdapter.CollectionViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder =
            CollectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.collections_list_item, parent, false))

    override fun getItemCount(): Int = items.size

    fun updateWordListItems(newItems : List<CollectionsModel>){
        val diffUtilCallback = DiffUtilCallback(oldWordList = items, newWordList = newItems)
        val diffRes = DiffUtil.calculateDiff(diffUtilCallback)
        items.clear()
        items.addAll(newItems)
        diffRes.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class CollectionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val binding by viewBinding(CollectionsListItemBinding::bind)
        fun bind(item : CollectionsModel){
            binding.textCollection.text = item.name
        }
    }
}