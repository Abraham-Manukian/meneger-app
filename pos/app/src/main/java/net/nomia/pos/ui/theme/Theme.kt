package net.nomia.pos.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Определяем палитру цветов
private val LightColorPalette = lightColors(
    primary = Color(0xFF6200EA), // Пример основного цвета
    primaryVariant = Color(0xFF3700B3), // Пример варианта основного цвета
    secondary = Color(0xFF03DAC5), // Пример вторичного цвета
)

private val DarkColorPalette = darkColors(
    primary = Color(0xFFBB86FC),
    primaryVariant = Color(0xFF3700B3),
    secondary = Color(0xFF03DAC5)
)

// Применение темы Material
@Composable
fun PosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        content = content
    )
}
