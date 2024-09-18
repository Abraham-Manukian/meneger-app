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
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Заголовок "Добро пожаловать"
        Text(
            text = stringResource(id = R.string.welcome),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Text(
            text = stringResource(id = R.string.form1_description),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.background
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле для ввода имени пользователя
        TextField(
            value = userName,
            onValueChange = onUserNameChange,
            label = { Text(text = stringResource(id = R.string.user_name_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Поле для ввода имени пользователя
        TextField(
            value = numberOrEmail,
            onValueChange = onNumberOrEmailChange,
            label = { Text(text = stringResource(id = R.string.number_or_email_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка "Продолжить"
        ContinueButton(onClick = onContinueClick)
    }
}
