package net.nomia.pos.ui.onboarding

import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.End
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import net.nomia.common.ui.composable.NomiaProgressBar
import net.nomia.common.ui.previews.ThemePreviews
import net.nomia.common.ui.theme.spacers

@Composable
internal fun OnboardingContent(
    currentStep: Int,
    totalSteps: Int,
    showSkip: Boolean = false,
    onSkipClicked: (() -> Unit)? = null,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()  // Контейнер занимает весь экран
            .padding(horizontal = 16.dp),  // Отступы по краям
        verticalArrangement = Arrangement.SpaceBetween  // Разделяем пространство сверху и снизу
    ) {
        // Верхняя часть с иконкой и кнопкой "Пропустить"
        TopSection(showSkip, onSkipClicked)

        // Основной контент (формы)
        Column(
            modifier = Modifier.weight(1f),  // Контент занимает всё оставшееся пространство
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
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


@ThemePreviews
@Suppress("StateFlowValueCalledInComposition")
@Composable
fun Onboarding(
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    PosTheme {
        // Получаем текущее состояние онбординга из ViewModel
        val currentStep by viewModel.currentStep.collectAsState()

        val userName by viewModel.userName.collectAsState()
        val numberOrEmail by viewModel.numberOrEmail.collectAsState()

        val storeName by viewModel.storeName.collectAsState()
        val countryAndCity by viewModel.countryAndCity.collectAsState()
        val address by viewModel.address.collectAsState()
        val isNewStore by viewModel.isNewStore.collectAsState()
        val automationSystem by viewModel.automationSystem.collectAsState()
        val selectedBusinessTypes by viewModel.selectedBusinessTypes.collectAsState()

        val selectedServices by viewModel.selectedServices.collectAsState()

        // Используем новый компонент OnboardingContent
        OnboardingContent(
            currentStep = currentStep,
            totalSteps = 6,
            onBackClick = { viewModel.goToPreviousStep() },
            onContinueClick = { viewModel.goToNextStep() }
        ) {
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Прогресс-бар из `common-ui`
                NomiaProgressBar(steps = 6, currentStep = currentStep)

                Spacer(modifier = Modifier.height(8.dp))
                // Уменьшаем расстояние между логотипом и прогресс-баром

                when (currentStep) {
                    1 -> WelcomeForm(
                        userName = userName,
                        onUserNameChange = { viewModel.onUserNameChange(it) },
                        numberOrEmail = numberOrEmail,
                        onNumberOrEmailChange = { viewModel.onNumberOrEmailChange(it) },
                        onContinueClick = { viewModel.goToNextStep() }
                    )

                    2 -> StoreDataForm(
                        storeName = storeName,
                        onStoreNameChange = viewModel::onStoreNameChange,
                        countryAndCity = countryAndCity,
                        onCountryAndCityChange = viewModel::onCountryAndCityChange,
                        address = address,
                        onAddressChange = viewModel::onAddressChange,
                        isNewStore = isNewStore,
                        onIsNewStoreChange = viewModel::onIsNewStoreChange,
                        automationSystem = automationSystem,
                        onAutomationSystemChange = viewModel::onAutomationSystemChange,
                        onContinueClick = { viewModel.goToNextStep() }
                    )

                    3 -> BusinessTypeForm(
                        selectedTypes = selectedBusinessTypes,
                        onTypeSelectChange = viewModel::onTypeSelectChange,
                        onContinueClick = { viewModel.goToNextStep() },
                        onSkipClick = { viewModel.goToNextStep() }
                    )

                    4 -> StoreSizeForm(
                        totalArea = viewModel.totalArea.value,
                        onTotalAreaChange = viewModel::onTotalAreaChange,
                        seatingCapacity = viewModel.seatingCapacity.value,
                        onSeatingCapacityChange = viewModel::onSeatingCapacityChange,
                        hallArea = viewModel.hallArea.value,
                        onHallAreaChange = viewModel::onHallAreaChange,
                        kitchenArea = viewModel.kitchenArea.value,
                        onKitchenAreaChange = viewModel::onKitchenAreaChange,
                        onContinueClick = { viewModel.goToNextStep() },
                        onSkipClick = { viewModel.goToNextStep() }
                    )

                    5 -> ServiceTypeForm(
                        selectedServices = selectedServices,
                        onServiceSelectChange = viewModel::onServiceSelectChange,
                        onContinueClick = { viewModel.goToNextStep() },
                        onSkipClick = { viewModel.goToNextStep() }
                    )

                    6 -> CompletionForm()

                }

            }
        }
    }
}
