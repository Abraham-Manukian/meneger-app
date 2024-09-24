package net.nomia.pos.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.nomia.pos.R
import net.nomia.common.ui.theme.appResources

@Composable
fun TopSection(showSkip: Boolean, onSkipClicked: (() -> Unit)?, currentStep: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Иконка приложения
        Icon(
            painter = painterResource(id = MaterialTheme.appResources.textLogoResId),
            tint = Color.Unspecified,
            contentDescription = null,
            modifier = Modifier.padding(16.dp)
        )

        // Кнопка "Пропустить", если её нужно показать
        if (showSkip == true && onSkipClicked != null ) {
            TextButton(onClick = onSkipClicked) {
                Text(text = stringResource(id = R.string.skip))
            }
        }
    }
}