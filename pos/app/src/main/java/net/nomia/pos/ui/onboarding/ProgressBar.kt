package net.nomia.pos.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.nomia.common.data.model.Theme

@Composable
fun ProgressBar(steps: Int, currentStep: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in 1..steps) {
            val color = if (i <= currentStep) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            Box(
                modifier = Modifier
                    .weight(1f)  // равное распределение ширины для каждого шага
                    .height(4.dp)
                    .background(color = color)
            )
            if (i < steps) {
                Spacer(modifier = Modifier.width(4.dp))  // разделитель между шагами
            }
        }
    }
}


