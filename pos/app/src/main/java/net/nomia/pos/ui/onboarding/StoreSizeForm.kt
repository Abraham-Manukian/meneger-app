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
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.nomia.pos.R

@Composable
fun StoreSizeForm(
    totalArea: String,
    onTotalAreaChange: (String) -> Unit,
    seatingCapacity: String,
    onSeatingCapacityChange: (String) -> Unit,
    hallArea: String,
    onHallAreaChange: (String) -> Unit,
    kitchenArea: String,
    onKitchenAreaChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        // Заголовок
        Text(
            text = stringResource(id = R.string.store_size_title),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Поля для ввода данных о площади помещения, количестве мест, площади залов и кухни
        OutlinedTextField(
            value = totalArea,
            onValueChange = onTotalAreaChange,
            label = { Text(text = stringResource(id = R.string.total_area_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = seatingCapacity,
            onValueChange = onSeatingCapacityChange,
            label = { Text(text = stringResource(id = R.string.seating_capacity_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = hallArea,
            onValueChange = onHallAreaChange,
            label = { Text(text = stringResource(id = R.string.hall_area_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = kitchenArea,
            onValueChange = onKitchenAreaChange,
            label = { Text(text = stringResource(id = R.string.kitchen_area_label)) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
