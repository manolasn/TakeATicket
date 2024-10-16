package gr.manolasn.takeaticket.ui.composables.splashScreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import gr.manolasn.takeaticket.R
import gr.manolasn.takeaticket.ui.composables.shared.TopBar
import gr.manolasn.takeaticket.ui.theme.White

@Composable
fun SplashScreen(
    mainScreen: () -> Unit
) {
    val scale = remember {
        Animatable(0f)
    }

    // Lottie composition for splash animation
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_anim))
    // State for Lottie animation progress
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1 // Run the animation only once
    )

    // When progress reaches 1.0f (end of animation), navigate to the main screen
    LaunchedEffect(progress) {
        if (progress == 1f) {
            mainScreen()
        }
    }

    LaunchedEffect(Unit) {
        // Animate the scale effect
        scale.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = 1000, easing = {
            OvershootInterpolator(0f).getInterpolation(it)
        }))
    }

    // Top bar and main content
    TopBar(
        title = String(),
        hasNavigation = false,
        topBarContainerColor = White,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = White),
            contentAlignment = Alignment.Center
        ) {
            // Lottie animation for splash screen
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
            )
        }
    }
}
