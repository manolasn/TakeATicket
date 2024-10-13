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
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    mainScreen: () -> Unit
) {

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(Unit) {
        delay(2000L)
        scale.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = 1000, easing = {
            OvershootInterpolator(0f).getInterpolation(it)
        }))
        delay(1000L)
        mainScreen()
    }

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

            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_anim))
            val progress by animateLottieCompositionAsState(composition)
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