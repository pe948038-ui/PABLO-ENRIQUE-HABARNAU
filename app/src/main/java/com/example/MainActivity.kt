package com.example

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        Surface(
          modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .testTag("main_screen"),
          color = Color(0xFF0F111A)
        ) {
          AhorcadoWebView()
        }
      }
    }
  }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AhorcadoWebView(modifier: Modifier = Modifier) {
  AndroidView(
    modifier = modifier
      .fillMaxSize()
      .testTag("hangman_webview"),
    factory = { context ->
      WebView(context).apply {
        settings.apply {
          javaScriptEnabled = true
          domStorageEnabled = true
          allowFileAccess = true
          cacheMode = WebSettings.LOAD_DEFAULT
          mediaPlaybackRequiresUserGesture = false
          loadWithOverviewMode = true
          useWideViewPort = true
        }
        setBackgroundColor(android.graphics.Color.parseColor("#0F111A"))
        webViewClient = WebViewClient()
        webChromeClient = WebChromeClient()
        loadUrl("file:///android_asset/ahorcado.html")
      }
    }
  )
}
