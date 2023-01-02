package com.metehanbolat.fakestorecompose.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun WaitingScreen(resId: Int) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId = resId))
    Box(
        modifier = Modifier.size(200.dp)
    ) {
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
    }
}