package com.clean.news.presentation.ui.main

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.clean.news.databinding.ArticleItemBinding
import com.clean.news.domain.model.Article
import com.clean.news.presentation.common.recyclerview.BaseRecyclerAdapter
import com.clean.news.presentation.common.recyclerview.BaseViewHolder
import com.clean.news.presentation.common.recyclerview.OnItemClickListener

class NewsListAdapter(
    articles: List<Article>,
    val onItemClickListener: OnItemClickListener
) :
    BaseRecyclerAdapter<Article, ArticleItemBinding>(ArrayList(articles)) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Article> =
        NewsViewHolder(
            inflateBinding<ArticleItemBinding>(parent, com.clean.news.R.layout.article_item)
        )

    inner class NewsViewHolder(private val binding: ArticleItemBinding) :
        BaseViewHolder<Article>(binding) {

        override fun bind(position: Int, item: Article) {
            binding.data = item
            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(it, position)
            }
            Glide.with(context).load(item.urlToImage).into(binding.thumbnail)
        }
    }
}

