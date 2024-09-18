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

    // Поля для первой формы
    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName

    private val _numberOrEmail = MutableStateFlow("")
    val numberOrEmail: StateFlow<String> = _numberOrEmail

    // Поля для второй формы
    private val _storeName = MutableStateFlow("")
    val storeName: StateFlow<String> = _storeName

    private val _countryAndCity = MutableStateFlow("")
    val countryAndCity: StateFlow<String> = _countryAndCity

    private val _address = MutableStateFlow("")
    val address: StateFlow<String> = _address

    private val _isNewStore = MutableStateFlow(false)
    val isNewStore: StateFlow<Boolean> = _isNewStore

    // Методы для обновления состояния
    fun onStoreNameChange(newName: String) {
        _storeName.value = newName
    }

    fun onCountryAndCityChange(newValue: String) {
        _countryAndCity.value = newValue
    }

    fun onAddressChange(newValue: String) {
        _address.value = newValue
    }

    fun onIsNewStoreChange(isNew: Boolean) {
        _isNewStore.value = isNew
    }

    // Функция для обновления имени пользователя
    fun onUserNameChange(newName: String) {
        _userName.value = newName
    }

    // Функция для обновления номера телефона или email
    fun onNumberOrEmailChange(newInput: String) {
        _numberOrEmail.value = newInput
    }

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

