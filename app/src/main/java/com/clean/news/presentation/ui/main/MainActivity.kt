package com.clean.news.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.clean.news.R
import com.clean.news.databinding.ActivityMainBinding
import com.clean.news.databinding.ArticleItemBinding
import com.clean.news.domain.model.Article
import com.clean.news.presentation.ui.web.WebActivity
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import slush.ItemListEditor
import slush.Slush
import slush.utils.BasicDiffCallback

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(
                this,
                R.layout.activity_main
            )
        binding.vm = mainViewModel
        binding.lifecycleOwner = this

        val listEditor = initNewsRecyclerView()

        mainViewModel.fetchNews()
        mainViewModel.articles.observe(this, Observer {
            it?.let {
                listEditor.changeAll(it)
            }
        })

        mainViewModel.searchQuery.observe(this, Observer {
            Logger.d(it)
            it?.let {
                mainViewModel.fetchNews()
            }
        })

        logo.setOnClickListener {
            Logger.d("Search Query = ${mainViewModel.searchQuery.value}")
        }
    }

    private fun initNewsRecyclerView(): ItemListEditor<Article> {
        return Slush.SingleType<Article>()
            .setItemLayout(R.layout.article_item)
            .setItems(mainViewModel.articles.value ?: listOf())
            .setLayoutManager(LinearLayoutManager(this))
            .onItemClick { _, position ->
                val intent = Intent(this, WebActivity::class.java)
                intent.putExtra("url", mainViewModel.getArticle(position)?.url)
                startActivity(intent)

                Logger.d("Clicked: ${mainViewModel.getArticle(position)?.urlToImage}")
            }
            .setDiffCallback(BasicDiffCallback())
            .onBindData<ArticleItemBinding> { itemBinding, article ->
                itemBinding.data = article
                itemBinding.thumbnail.clipToOutline = true

                if (!article.urlToImage.isNullOrEmpty()) {
                    Glide.with(this).load(article.urlToImage).into(itemBinding.thumbnail)
                }
            }
            .into(recyclerView)
            .itemListEditor
    }
}
