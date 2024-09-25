package net.nomia.pos.ui.onboarding.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
internal fun ServiceTypeForm(
    selectedServices: List<String>,
    onServiceSelectChange: (String) -> Unit,
    onSkipChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = stringResource(id = R.string.service_type_title),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(16.dp))

        val serviceTypes = listOf(
            stringResource(id = R.string.takeaway) to painterResource(id = R.drawable.ic_restaurant),
            stringResource(id = R.string.in_the_establishment) to painterResource(id = R.drawable.ic_bar),
            stringResource(id = R.string.delivery) to painterResource(id = R.drawable.ic_cafe)
        )

        serviceTypes.forEach { (service, icon) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onServiceSelectChange(service) }
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
                    text = service,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                NomiaCheckBox(
                    isChecked = selectedServices.contains(service),
                    onCheckedChange = { onServiceSelectChange(service) }
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

