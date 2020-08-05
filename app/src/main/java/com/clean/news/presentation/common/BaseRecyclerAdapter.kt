package com.clean.news.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<ITEM, R>(private val items: MutableList<ITEM>) :
    RecyclerView.Adapter<BaseViewHolder<ITEM>>() {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<ITEM>, position: Int) {
        holder.bind(position, items[position])
    }

    fun <R : ViewDataBinding> inflateBinding(viewGroup: ViewGroup, layoutId: Int): R =
        DataBindingUtil.inflate<R>(
            LayoutInflater.from(viewGroup.context), layoutId, viewGroup, false
        )
}
