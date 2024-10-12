package net.nomia.pos.ui.onboarding

import android.content.Context
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

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName

    private val _numberOrEmail = MutableStateFlow("")
    val numberOrEmail: StateFlow<String> = _numberOrEmail

    private val _storeName = MutableStateFlow("")
    val storeName: StateFlow<String> = _storeName

    private val _countryAndCity = MutableStateFlow("")
    val countryAndCity: StateFlow<String> = _countryAndCity

    private val _address = MutableStateFlow("")
    val address: StateFlow<String> = _address

    private val _isNewStore = MutableStateFlow(false)
    val isNewStore: StateFlow<Boolean> = _isNewStore

    private val _automationSystem = MutableStateFlow("")
    val automationSystem: StateFlow<String> = _automationSystem

    private val _selectedBusinessTypes = MutableStateFlow<List<String>>(emptyList())
    val selectedBusinessTypes: StateFlow<List<String>> = _selectedBusinessTypes

    private val _totalArea = MutableStateFlow("")
    val totalArea: StateFlow<String> = _totalArea

    private val _seatingCapacity = MutableStateFlow("")
    val seatingCapacity: StateFlow<String> = _seatingCapacity

    private val _hallArea = MutableStateFlow("")
    val hallArea: StateFlow<String> = _hallArea

    private val _kitchenArea = MutableStateFlow("")
    val kitchenArea: StateFlow<String> = _kitchenArea

    private val _selectedServices = MutableStateFlow<List<String>>(emptyList())
    val selectedServices: StateFlow<List<String>> = _selectedServices

    private val _showSkip = MutableStateFlow(false)
    val showSkip: StateFlow<Boolean> = _showSkip


    fun updateSkipButtonState(shouldShowSkip: Boolean) {
        _showSkip.value = shouldShowSkip
     }

    fun onServiceSelectChange(service: String) {
        val currentServices = _selectedServices.value.toMutableList()
        if (currentServices.contains(service)) {
            currentServices.remove(service)
        } else {
            currentServices.add(service)
        }
        _selectedServices.value = currentServices
    }

    fun onTotalAreaChange(newValue: String) {
        _totalArea.value = newValue
    }

    fun onSeatingCapacityChange(newValue: String) {
        _seatingCapacity.value = newValue
    }

    fun onHallAreaChange(newValue: String) {
        _hallArea.value = newValue
    }

    fun onKitchenAreaChange(newValue: String) {
        _kitchenArea.value = newValue
    }

    fun onTypeSelectChange(type: String) {
        val currentTypes = _selectedBusinessTypes.value.toMutableList()
        if (currentTypes.contains(type)) {
            currentTypes.remove(type)
        } else {
            currentTypes.add(type)
        }
        _selectedBusinessTypes.value = currentTypes
    }

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

    fun onAutomationSystemChange(newSystem: String) {
        _automationSystem.value = newSystem
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

