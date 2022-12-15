package com.djr.tabnews.core.uikit.components.tnScaffold

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.djr.tabnews.core.uikit.R
import com.djr.tabnews.core.uikit.theme.TabNewsTheme

@Composable
fun TnScaffold(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    isError: Boolean = false,
    content: (@Composable () -> Unit)
) {
    Box(
        modifier = modifier
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {

        AnimatedVisibility(
            visible = isLoading && isError.not(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            TnLoading()
        }
        AnimatedVisibility(
            visible = isLoading.not() && isError.not(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            content.invoke()
        }
    }
}

@Composable
fun TnErrorScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Oops, something went wrong!",
            style = TabNewsTheme.typography.HeadingH3,
            color = TabNewsTheme.colors.textLight
        )
    }
}

@Preview
@Composable
fun TnLoading() {
    val lottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.coin_loading)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row {
            LottieAnimation(
                modifier = Modifier
                    .size(150.dp),
                composition = lottieComposition,
                iterations = LottieConstants.IterateForever
            )
            Spacer(modifier = Modifier.width(15.dp)) // Alignment was looking weird
        }
        Text(
            text = "Carregando...",
            style = TabNewsTheme.typography.HeadingH3,
            color = TabNewsTheme.colors.textLight
        )
    }
}