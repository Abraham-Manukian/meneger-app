package net.nomia.pos.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.nomia.pos.R

@Composable
fun StoreDataForm(
    storeName: String,
    onStoreNameChange: (String) -> Unit,
    countryAndCity: String,
    onCountryAndCityChange: (String) -> Unit,
    address: String,
    onAddressChange: (String) -> Unit,
    isNewStore: Boolean,
    onIsNewStoreChange: (Boolean) -> Unit,
    onContinueClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Заголовок "Создайте первое заведение"
        Text(
            text = stringResource(id = R.string.create_first_store),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = stringResource(id = R.string.form2_description),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.background
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле для ввода названия заведения
        TextField(
            value = storeName,
            onValueChange = onStoreNameChange,
            label = { Text(text = stringResource(id = R.string.store_name_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле для ввода страны и города
        TextField(
            value = countryAndCity,
            onValueChange = onCountryAndCityChange,
            label = { Text(text = stringResource(id = R.string.country_and_city_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле для ввода адреса
        TextField(
            value = address,
            onValueChange = onAddressChange,
            label = { Text(text = stringResource(id = R.string.address_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Чекбокс "Это новое заведение"
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isNewStore,
                onCheckedChange = onIsNewStoreChange
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(id = R.string.is_new_store_label))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка "Продолжить"
        ContinueButton(onClick = onContinueClick)
    }
}
