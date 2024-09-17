package net.nomia.pos.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.nomia.auth.domain.LogoutUseCase
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(

    private val logoutUseCase : LogoutUseCase
) : ViewModel() {

    // Храним текущее состояние шага онбординга
    private val _currentStep = MutableStateFlow(1)
    val currentStep: StateFlow<Int> = _currentStep




    fun logOut() = viewModelScope.launch {
        logoutUseCase.invoke()
    }

    // Функция для перехода на следующий шаг
    fun goToNextStep() {
        if (_currentStep.value < 6) {
            _currentStep.value = _currentStep.value + 1
        }
    }

    // Функция для возврата на предыдущий шаг
    fun goToPreviousStep() {
        if (_currentStep.value > 1) {
            _currentStep.value = _currentStep.value - 1
        }
    }
}

