package net.nomia.common.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
    checkedColor: Color = MaterialTheme.colorScheme.surfaceTint,   // Цвет активного состояния
    uncheckedBorderColor: Color = MaterialTheme.colorScheme.onSurfaceVariant // Цвет границы для неактивного состояния
) {
    Box(
        modifier = modifier
            .size(18.dp)  // Размер чекбокса 18x18px
            .border(
                width = 2.dp,  // Ширина обводки 2px
                color = if (!isChecked) uncheckedBorderColor else checkedColor,  // Цвет обводки для неактивного состояния
                shape = RoundedCornerShape(topStart = 2.dp)  // Скругленные углы
            )
            .background(
                color = if (isChecked) checkedColor else Color.Transparent,  // Фон для активного состояния
                shape = RoundedCornerShape(topStart = 2.dp)  // Скругленные углы
            )
            .clickable { onCheckedChange(!isChecked) },  // Изменение состояния при клике
        contentAlignment = Alignment.Center
    ) {
        if (isChecked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check_24),  // Иконка галочки
                contentDescription = null,
                tint = Color.White,  // Белая галочка
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
