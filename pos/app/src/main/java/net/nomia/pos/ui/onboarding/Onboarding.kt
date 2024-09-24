package net.nomia.pos.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import net.nomia.pos.ui.theme.PosTheme
import net.nomia.common.ui.composable.NomiaProgressBar
import net.nomia.common.ui.previews.ThemePreviews

@Composable
internal fun OnboardingContent(
    currentStep: Int,
    totalSteps: Int,
    showSkip: Boolean,
    onSkipClicked: (() -> Unit)? = null,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Верхняя часть с иконкой и кнопкой "Пропустить"
        TopSection(
            showSkip = showSkip,
            onSkipClicked = onSkipClicked,
            currentStep = currentStep
        )

        // Основной контент (формы)
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }

        // Кнопки "Назад" и "Продолжить"
        FormButtons(
            currentStep = currentStep,
            totalSteps = totalSteps,
            onBackClick = onBackClick,
            onContinueClick = onContinueClick
        )
    }
}


@Composable
fun Onboarding(
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    PosTheme {
        val currentStep by viewModel.currentStep.collectAsState()
        val showSkip by viewModel.showSkip.collectAsState()

        OnboardingContent(
            currentStep = currentStep,
            totalSteps = 6,
            showSkip = showSkip,
            onSkipClicked = { viewModel.goToNextStep() },
            onBackClick = { viewModel.goToPreviousStep() },
            onContinueClick = { viewModel.goToNextStep() }
        ) {
            // Основной контент форм
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Прогресс-бар
                NomiaProgressBar(steps = 6, currentStep = currentStep)

                Spacer(modifier = Modifier.height(8.dp))

                when (currentStep) {
                    1 -> WelcomeForm(
                        userName = viewModel.userName.collectAsState().value,
                        onUserNameChange = { viewModel.onUserNameChange(it) },
                        numberOrEmail = viewModel.numberOrEmail.collectAsState().value,
                        onNumberOrEmailChange = { viewModel.onNumberOrEmailChange(it) },
                        onContinueClick = { viewModel.goToNextStep()},
                        onSkipChange = { viewModel.updateSkipButtonState(it) }
                    )


                    2 -> StoreDataForm(
                        storeName = viewModel.storeName.collectAsState().value,
                        onStoreNameChange = viewModel::onStoreNameChange,
                        countryAndCity = viewModel.countryAndCity.collectAsState().value,
                        onCountryAndCityChange = viewModel::onCountryAndCityChange,
                        address = viewModel.address.collectAsState().value,
                        onAddressChange = viewModel::onAddressChange,
                        isNewStore = viewModel.isNewStore.collectAsState().value,
                        onIsNewStoreChange = viewModel::onIsNewStoreChange,
                        automationSystem = viewModel.automationSystem.collectAsState().value,
                        onAutomationSystemChange = viewModel::onAutomationSystemChange,
                        onSkipChange = { viewModel.updateSkipButtonState(it) }
                    )

                    3 -> BusinessTypeForm(
                        selectedTypes = viewModel.selectedBusinessTypes.collectAsState().value,
                        onTypeSelectChange = viewModel::onTypeSelectChange,
                        onSkipChange = { viewModel.updateSkipButtonState(it) }
                    )

                    4 -> StoreSizeForm(
                        totalArea = viewModel.totalArea.collectAsState().value,
                        onTotalAreaChange = viewModel::onTotalAreaChange,
                        seatingCapacity = viewModel.seatingCapacity.collectAsState().value,
                        onSeatingCapacityChange = viewModel::onSeatingCapacityChange,
                        hallArea = viewModel.hallArea.collectAsState().value,
                        onHallAreaChange = viewModel::onHallAreaChange,
                        kitchenArea = viewModel.kitchenArea.collectAsState().value,
                        onKitchenAreaChange = viewModel::onKitchenAreaChange,
                        onSkipChange = { viewModel.updateSkipButtonState(it) }
                    )

                    5 -> ServiceTypeForm(
                        selectedServices = viewModel.selectedServices.collectAsState().value,
                        onServiceSelectChange = viewModel::onServiceSelectChange,
                        onSkipChange = { viewModel.updateSkipButtonState(it) }
                    )

                    6 -> CompletionForm(
                        onSkipChange = { viewModel.updateSkipButtonState(it) }
                    )
                }
            }
        }
    }
}




