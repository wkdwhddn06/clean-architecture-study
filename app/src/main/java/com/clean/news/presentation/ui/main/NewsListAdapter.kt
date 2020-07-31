package com.clean.news.presentation.ui.main

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.clean.news.R
import com.clean.news.databinding.ArticleItemBinding
import com.clean.news.domain.model.Article
import com.clean.news.presentation.common.BaseRecyclerAdapter
import com.clean.news.presentation.common.BaseViewHolder

class NewsListAdapter(private val articles: MutableList<Article>) :
    BaseRecyclerAdapter<Article>(articles) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Article> =
        NewsViewHolder(inflateBinding<ArticleItemBinding>(parent, R.layout.article_item))

    class NewsViewHolder(private val binding: ArticleItemBinding) :
        BaseViewHolder<Article>(binding) {
        private val context = binding.root.context

        override fun bind(position: Int, item: Article) {
            binding.data = item
            Glide.with(context).load(item.urlToImage).into(binding.thumbnail)
        }
    }
}

