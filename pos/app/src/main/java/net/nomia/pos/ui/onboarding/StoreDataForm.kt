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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.nomia.common.ui.composable.NomiaCheckBox
import net.nomia.common.ui.composable.NomiaOutlinedTextField
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
    automationSystem: String,
    onAutomationSystemChange: (String) -> Unit,
    onSkipChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        // Заголовок "Создайте первое заведение"
        Text(
            text = stringResource(id = R.string.create_first_store),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = stringResource(id = R.string.form2_description),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле для ввода названия заведения
        NomiaOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = storeName,
            onValueChange = onStoreNameChange,
            placeholder = { Text(
                text = stringResource(id = R.string.store_name_label),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant) }
        )

        Text(text = stringResource(
            id = R.string.form2_description_store_name),
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Поле для ввода страны и города
        NomiaOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = countryAndCity,
            onValueChange = onCountryAndCityChange,
            placeholder = { Text(
                text = stringResource(id = R.string.country_and_city_label),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,) }
        )

        Text(text = stringResource(
            id = R.string.form2_description_country_and_city),
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле для ввода адреса
        NomiaOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = address,
            onValueChange = onAddressChange,
            placeholder = { Text(
                text = stringResource(id = R.string.address_label),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Чекбокс "Это новое заведение"
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            NomiaCheckBox(
                isChecked = isNewStore,
                onCheckedChange = onIsNewStoreChange
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
            ){
                Text(text = stringResource(
                    id = R.string.is_new_store_label),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = stringResource(
                    id = R.string.form2_description_check_box),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium
                )
            }


        }

        // Поле "Система автоматизации" отображается, если чекбокс НЕ нажат
        if (!isNewStore) {
            Spacer(modifier = Modifier.height(16.dp))

            // Поле для ввода системы автоматизации
            NomiaOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = automationSystem,
                onValueChange = onAutomationSystemChange,
                placeholder = { Text(
                    text = stringResource(id = R.string.automation_system_label),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,) }
            )

            Text(text = stringResource(
                id = R.string.form2_description_automation_system),
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
    onSkipChange(false)
}

