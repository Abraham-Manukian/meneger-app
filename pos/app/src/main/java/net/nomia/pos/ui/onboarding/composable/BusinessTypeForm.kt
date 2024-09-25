package net.nomia.pos.ui.onboarding.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.nomia.common.ui.composable.NomiaCheckBox
import net.nomia.pos.R

@Composable
internal fun BusinessTypeForm(
    selectedTypes: List<String>,
    onTypeSelectChange: (String) -> Unit,
    onSkipChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = stringResource(id = R.string.business_type_title),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Text(
            text = stringResource(id = R.string.form3_description),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.height(16.dp))

        val businessTypes = listOf(
            stringResource(id = R.string.restaurant) to painterResource(id = R.drawable.ic_restaurant),
            stringResource(id = R.string.bar) to painterResource(id = R.drawable.ic_bar),
            stringResource(id = R.string.cafe) to painterResource(id = R.drawable.ic_cafe),
            stringResource(id = R.string.dining_room) to painterResource(id = R.drawable.ic_dining_room),
            stringResource(id = R.string.coffee_shop) to painterResource(id = R.drawable.ic_coffee_shop),
            stringResource(id = R.string.cooking) to painterResource(id = R.drawable.ic_cooking),
            stringResource(id = R.string.other) to painterResource(id = R.drawable.ic_other)
        )

        businessTypes.forEach { (type, icon) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onTypeSelectChange(type) }
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = type,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                NomiaCheckBox(
                    isChecked = selectedTypes.contains(type),
                    onCheckedChange = { onTypeSelectChange(type) }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Divider(
                color = MaterialTheme.colorScheme.outline,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
    onSkipChange(true)
}

