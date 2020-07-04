package com.clean.news.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.clean.news.R
import com.clean.news.data.model.Article
import com.clean.news.databinding.ItemLayoutBinding
import com.clean.news.presentation.ui.web.WebActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import slush.Slush
import slush.utils.BasicDiffCallback

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listEditor = Slush.SingleType<Article>()
            .setItemLayout(R.layout.item_layout)
            .setItems(listOf())
            .setLayoutManager(LinearLayoutManager(this))
            .onItemClick { _, position ->
                val intent = Intent(this, WebActivity::class.java)
                intent.putExtra("url", mainViewModel.getArticle(position)?.url)
                startActivity(intent)

                Log.d("Main", "Clicked: ${mainViewModel.getArticle(position)?.urlToImage}")
            }
            .setDiffCallback(BasicDiffCallback())
            .onBindData<ItemLayoutBinding> { binding, article ->
                binding.data = article;
                binding.thumbnail.clipToOutline = true

                if (!article.urlToImage.isNullOrEmpty()) {
                    Glide.with(this).load(article.urlToImage).into(binding.thumbnail)
                    Log.d("BindData", "Bind : ${article.title}")
                    Log.d("BindData", "Bind Image: ${article.urlToImage}")
                }
            }
            .into(recyclerView)
            .itemListEditor;

        mainViewModel.fetchNews()

        mainViewModel.articles.observe(this, Observer {
            it?.let {
                listEditor.changeAll(it)
            }
        })
    }

}
