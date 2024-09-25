package net.nomia.pos.ui.onboarding.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.nomia.pos.R
import net.nomia.common.ui.theme.appResources

@Composable
internal fun TopSection(
    showSkip: Boolean,
    onSkipClicked: (() -> Unit)?,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Иконка приложения
        Icon(
            painter = painterResource(id = MaterialTheme.appResources.textLogoResId),
            tint = Color.Unspecified,
            contentDescription = null,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        if (showSkip && onSkipClicked != null) {
            TextButton(
                onClick = onSkipClicked,
            ) {
                Text(
                    text = stringResource(id = R.string.skip),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}