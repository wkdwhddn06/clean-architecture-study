package com.clean.news.presentation.ui.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.clean.news.R
import kotlinx.android.synthetic.main.activity_webview.*

class WebActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val url = intent.getStringExtra("url")

        webView.run {
            webChromeClient = WebChromeClient()
            webViewClient = WebViewClientClass()
            settings.javaScriptEnabled = true
            loadUrl(url)
        }
    }

    private class WebViewClientClass : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView,
            url: String
        ): Boolean {
            Log.d("check URL", url)
            view.loadUrl(url)
            return true
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean { //뒤로가기 버튼 이벤트
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) { //웹뷰에서 뒤로가기 버튼을 누르면 뒤로가짐
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
