package net.nomia.common.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import net.nomia.common.ui.R

@Composable
fun NomiaCheckBox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    checkedColor: Color = MaterialTheme.colorScheme.surfaceTint,
    uncheckedColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .background(
                color = if (isChecked) checkedColor else uncheckedColor,
                shape = RoundedCornerShape(4.dp) // Скругленные углы
            )
            .clickable { onCheckedChange(!isChecked) }, // Изменение состояния при клике
        contentAlignment = Alignment.Center
    ) {
        if (isChecked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check_24), // Иконка галочки
                contentDescription = null,
                tint = Color.White, // Белая галочка
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
