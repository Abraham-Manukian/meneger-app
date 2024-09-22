package net.nomia.pos.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.nomia.common.ui.composable.NomiaOutlinedTextField
import net.nomia.pos.R



@Composable
fun WelcomeForm(
    userName: String,
    onUserNameChange: (String) -> Unit,
    numberOrEmail: String,
    onNumberOrEmailChange: (String) -> Unit,
    onContinueClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,

    ) {
        // Заголовок "Добро пожаловать"
        Text(
            text = stringResource(id = R.string.welcome),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Text(
            text = stringResource(id = R.string.form1_description),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.height(16.dp))

        NomiaOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = userName,
            onValueChange = onUserNameChange,
            placeholder = { Text(text = stringResource(id = R.string.user_name_label)) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        NomiaOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = numberOrEmail,
            onValueChange = onNumberOrEmailChange,
            placeholder = { Text(text = stringResource(id = R.string.number_or_email_label)) }
        )
    }
}
