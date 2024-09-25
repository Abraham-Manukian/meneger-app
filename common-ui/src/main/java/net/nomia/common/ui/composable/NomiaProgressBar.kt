package net.nomia.common.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NomiaProgressBar(
    steps: Int,
    currentStep: Int,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = MaterialTheme.colorScheme.secondary,
    separatorWidth: Dp = 4.dp,
    height: Dp = 4.dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in 1..steps) {
            val color = if (i <= currentStep) activeColor else inactiveColor
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(height)
                    .background(color = color)
            )
            if (i < steps) {
                Spacer(modifier = Modifier.width(separatorWidth))
            }
        }
    }
}
