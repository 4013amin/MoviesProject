package com.example.movies.MainUi

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.example.movies.data.viewModels.MovieViewModel
import com.example.movies.navigations.navigation
import com.example.movies.ui.theme.MoviesTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MovieViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {

                WindowCompat.setDecorFitsSystemWindows(window, false)

                window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                )

                val linearGradientBrush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFB226E1),
                        Color(0xFFFC6603),
                        Color(0xFF5995EE),
                        Color(0xFF3D3535)
                    ),
                    start = Offset(Float.POSITIVE_INFINITY, 0F),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent), // Set to transparent to see the gradient
                ) {
                    // Background with the linear gradient
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(linearGradientBrush)
                    ) {
                        // Navigation and content
                        navigation()
                    }
                }
            }
        }
    }
}
