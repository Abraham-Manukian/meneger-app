package net.nomia.pos.ui.onboarding

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import net.nomia.common.ui.composable.NomiaScrollableScaffold
import net.nomia.common.ui.theme.appResources
import net.nomia.pos.R

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun Onboarding(
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val activity = (LocalContext.current as Activity)
    val windowSizeClass = calculateWindowSizeClass(activity)

    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium-> {
            // Layout for smaller screens (most phones in portrait)
        }
        WindowWidthSizeClass.Expanded -> {
            // Layout for larger screens (tablets, desktops)
        }
    }

    NomiaScrollableScaffold(
        title = {
            Icon(
                painter = painterResource(id = MaterialTheme.appResources.textLogoResId),
                tint = Color.Unspecified,
                contentDescription = null
            )
        },
        actions = {
            Text(
                stringResource(R.string.skip),
                modifier = Modifier.clickable {

                }
            )
        },
    ) {
        /**
         * YOUR CODE GOES HERE
         */
    }
}
