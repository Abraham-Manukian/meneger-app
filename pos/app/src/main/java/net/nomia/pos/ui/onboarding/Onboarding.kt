package net.nomia.pos.ui.onboarding

import android.widget.ProgressBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import net.nomia.common.ui.composable.NomiaScrollableScaffold
import net.nomia.common.ui.composable.ScreenTitleText
import net.nomia.common.ui.theme.appResources
import net.nomia.pos.R
import net.nomia.pos.ui.theme.PosTheme

@Preview
@Composable
fun Onboarding(
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    PosTheme {
        // Получаем текущее состояние онбординга из ViewModel
        val currentStep by viewModel.currentStep.collectAsState()

        Icon(
            painter = painterResource(id = MaterialTheme.appResources.textLogoResId),
            tint = Color.Unspecified,
            contentDescription = null
        )
        NomiaScrollableScaffold(
            title = { ScreenTitleText(text = stringResource(id = R.string.onboarding)) },
            actions = {
                Icon(
                    painter = painterResource(id = MaterialTheme.appResources.textLogoResId),
                    tint = Color.Unspecified,
                    contentDescription = null
                )
            },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Прогресс-бар
                ProgressBar(steps = 6, currentStep = currentStep)

                Spacer(modifier = Modifier.height(16.dp))

                // Здесь могут быть другие компоненты, отображающие текущий экран онбординга
                Text(text = "Step $currentStep of 6")
            }
        }
    }
}


