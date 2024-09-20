package net.nomia.pos.ui.onboarding

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.nomia.pos.R


@Composable
fun BusinessTypeForm(
    selectedTypes: List<String>,
    onTypeSelectChange: (String) -> Unit,
    onContinueClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Заголовок
        Text(
            text = stringResource(id = R.string.business_type_title),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Чекбоксы для выбора типов заведений
        val businessTypes = listOf(
            stringResource(id = R.string.restaurant),
            stringResource(id = R.string.bar),
            stringResource(id = R.string.cafe),
            stringResource(id = R.string.dining_room),
            stringResource(id = R.string.coffee_shop),
            stringResource(id = R.string.cooking),
            stringResource(id = R.string.other)

        )

        businessTypes.forEach { type ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onTypeSelectChange(type) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = selectedTypes.contains(type),
                    onCheckedChange = { onTypeSelectChange(type) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = type)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопки "Пропустить" и "Продолжить"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = onSkipClick) {
                Text(text = stringResource(id = R.string.skip_button))
            }

            ContinueButton(onClick = onContinueClick)
        }
    }
}
