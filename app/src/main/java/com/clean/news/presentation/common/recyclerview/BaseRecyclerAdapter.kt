package com.clean.news.presentation.common.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<ITEM, R>(private val items: ArrayList<ITEM>) :
    RecyclerView.Adapter<BaseViewHolder<ITEM>>() {

    private val diffCallback: BasicDiffCallback<ITEM> = BasicDiffCallback<ITEM>()

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<ITEM>, position: Int) {
        holder.bind(position, items[position])
    }

    fun <R : ViewDataBinding> inflateBinding(viewGroup: ViewGroup, layoutId: Int): R =
        DataBindingUtil.inflate<R>(
            LayoutInflater.from(viewGroup.context), layoutId, viewGroup, false
        )

    // region ItemManageFunctions
    fun addItem(item: ITEM) {
        items.add(item)
        notifyItemInserted(itemCount)
    }

    fun addItem(position: Int, item: ITEM) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    fun addItems(items: List<ITEM>) {
        this.items.addAll(items)
        notifyItemRangeInserted(itemCount, items.size)
    }

    fun addItems(startPosition: Int, items: List<ITEM>) {
        this.items.addAll(startPosition, items)
        notifyItemRangeInserted(startPosition, items.size)
    }

    fun removeItem(item: ITEM): Int {
        val index = items.indexOf(item)
        if (index >= 0) {
            items.removeAt(index)
            notifyItemRemoved(index)
        }
        return index
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeItems(startPosition: Int, itemCount: Int) {
        items.subList(startPosition, startPosition + itemCount).clear()
        notifyItemRangeRemoved(startPosition, itemCount)
    }

    fun changeItem(position: Int, item: ITEM) {
        items[position] = item
        notifyItemChanged(position)
    }

    fun changeItems(startPosition: Int, items: List<ITEM>) {
        this.items.subList(startPosition, startPosition + items.size).clear()
        this.items.addAll(startPosition, items)
        notifyItemRangeChanged(startPosition, items.size)
    }

    fun changeAll(items: List<ITEM>) {
        diffCallback.setOldItems(this.items)
        diffCallback.setNewItems(items)
        DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(this)

        this.items.clear()
        this.items.addAll(items)
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        val removedItem = items.removeAt(fromPosition)
        items.add(toPosition, removedItem)
        notifyItemMoved(fromPosition, toPosition)
    }
    //endregion
}
