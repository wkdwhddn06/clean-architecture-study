package com.clean.news.presentation.common.recyclerview

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<ITEM>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    protected val context: Context = binding.root.context

    abstract fun bind(position: Int, item: ITEM)
}
