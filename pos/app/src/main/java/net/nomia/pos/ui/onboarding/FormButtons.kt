package net.nomia.pos.ui.onboarding

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import net.nomia.pos.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import net.nomia.common.ui.composable.NomiaFilledButton

@Composable
fun FormButtons(
    currentStep: Int,
    totalSteps: Int,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit,
) {
    Column {
        Divider(
            color = MaterialTheme.colorScheme.outline,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Кнопка "Назад", показываем, если не первый и не последний шаг
            if (currentStep > 1 && currentStep < totalSteps) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(48.dp)
                        .border(1.dp, MaterialTheme.colorScheme.onBackground, CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            // Кнопка "Продолжить", показываем, если не последний шаг
            if (currentStep < totalSteps) {
                ContinueButton(
                    onClick = onContinueClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = if (currentStep > 1) 16.dp else 0.dp)
                )
            }
        }
    }
}

@Composable
fun ContinueButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    NomiaFilledButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.continue_button),
            color = Color.White
        )
    }
}
