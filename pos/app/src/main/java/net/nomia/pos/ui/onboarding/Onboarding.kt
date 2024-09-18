package net.nomia.pos.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import net.nomia.common.ui.theme.appResources
import net.nomia.pos.R
import net.nomia.pos.ui.theme.PosTheme


@Composable
internal fun OnboardingContent(
    showSkip: Boolean = false,  // Показывать кнопку "Пропустить"
    onSkipClicked: (() -> Unit)? = null,  // Действие по клику на "Пропустить"
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Иконка приложения слева
            Icon(
                painter = painterResource(id = MaterialTheme.appResources.textLogoResId),
                tint = Color.Unspecified,
                contentDescription = null
            )

            // Если нужно, показываем кнопку "Пропустить"
            if (showSkip && onSkipClicked != null) {
                TextButton(
                    onClick = onSkipClicked
                ) {
                    Text(text = stringResource(id = R.string.skip))
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Контент, который будет находиться под иконкой и кнопкой "Пропустить"
        content()
    }
}

@Composable
fun Onboarding(
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    PosTheme {
        // Получаем текущее состояние онбординга из ViewModel
        val currentStep by viewModel.currentStep.collectAsState()

        val userName by viewModel.userName.collectAsState()
        val numberOrEmail by viewModel.numberOrEmail.collectAsState()

        // Используем новый компонент OnboardingContent
        OnboardingContent(
            showSkip = currentStep < 6,  // Показываем кнопку "Пропустить", если это не последний шаг
            onSkipClicked = { viewModel.goToNextStep() },  // Используем goToNextStep вместо skipOnboarding
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Прогресс-бар
                ProgressBar(steps = 6, currentStep = currentStep)

                Spacer(modifier = Modifier.height(8.dp))  // Уменьшаем расстояние между логотипом и прогресс-баром

                when (currentStep) {
                    1 -> WelcomeForm(
                        userName = userName,
                        onUserNameChange = { viewModel.onUserNameChange(it) },
                        numberOrEmail = numberOrEmail,
                        onNumberOrEmailChange = { viewModel.onNumberOrEmailChange(it) },
                        onContinueClick = { viewModel.goToNextStep() }
                    )
                }
            }
        }
    }
}






