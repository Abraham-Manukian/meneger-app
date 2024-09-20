package net.nomia.pos.ui.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.nomia.common.ui.theme.NomiaThemeMaterial3

@Composable
fun BaseOnboardingLayout(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    content: @Composable () -> Unit
) {
    NomiaThemeMaterial3 {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .systemBarsPadding()
                .then(modifier)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                content()
            }
        }
    }
}
