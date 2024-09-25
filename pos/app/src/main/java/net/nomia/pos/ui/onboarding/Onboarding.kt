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
import net.nomia.common.ui.composable.NomiaProgressBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.ui.platform.LocalContext
import android.app.Activity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import net.nomia.pos.ui.onboarding.composable.BusinessTypeForm
import net.nomia.pos.ui.onboarding.composable.CompletionForm
import net.nomia.pos.ui.onboarding.composable.FormButtons
import net.nomia.pos.ui.onboarding.composable.ServiceTypeForm
import net.nomia.pos.ui.onboarding.composable.StoreDataForm
import net.nomia.pos.ui.onboarding.composable.StoreSizeForm
import net.nomia.pos.ui.onboarding.composable.TopSection
import net.nomia.pos.ui.onboarding.composable.WelcomeForm

@Composable
internal fun OnboardingContent(
    currentStep: Int,
    totalSteps: Int,
    isTablet: Boolean,
    showSkip: Boolean = false,
    onSkipClicked: (() -> Unit)? = null,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit,
    content: @Composable () -> Unit,
) {

    val horizontalPadding = if (isTablet) 360.dp else 16.dp

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopSection(
                showSkip = showSkip,
                onSkipClicked = onSkipClicked,
            )

            NomiaProgressBar(steps = totalSteps, currentStep = currentStep)
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            content()
        }

        Divider(
            color = MaterialTheme.colorScheme.outline,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FormButtons(
                currentStep = currentStep,
                totalSteps = totalSteps,
                onBackClick = onBackClick,
                onContinueClick = onContinueClick,
                isTablet = isTablet
            )
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun Onboarding(
    viewModel: OnboardingViewModel = hiltViewModel()
) {

    val currentStep by viewModel.currentStep.collectAsState()
    val showSkip by viewModel.showSkip.collectAsState()
    val context = LocalContext.current
    val windowSizeClass = calculateWindowSizeClass(context as Activity)

    val isTablet = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium || windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded

    OnboardingContent(
        currentStep = currentStep,
        totalSteps = 6,
        showSkip = showSkip,
        isTablet = isTablet,
        onSkipClicked = { viewModel.goToNextStep() },
        onBackClick = { viewModel.goToPreviousStep() },
        onContinueClick = { viewModel.goToNextStep() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (currentStep) {
                1 -> WelcomeForm(
                    userName = viewModel.userName.collectAsState().value,
                    onUserNameChange = { viewModel.onUserNameChange(it) },
                    numberOrEmail = viewModel.numberOrEmail.collectAsState().value,
                    onNumberOrEmailChange = { viewModel.onNumberOrEmailChange(it) },
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
