package com.djr.tabnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.djr.tabnews.uikit.LocalColors
import com.djr.tabnews.uikit.TabNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabNewsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LocalColors.current.primary
                ) {}
            }
        }
    }
}
