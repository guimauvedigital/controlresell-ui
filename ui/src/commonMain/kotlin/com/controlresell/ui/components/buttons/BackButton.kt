package com.controlresell.ui.components.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.controlresell.ui.theme.LocalAppColors
import com.controlresell.ui.theme.LocalAppTypography

@Composable
fun BackButton(
    label: String? = null,
    onBack: (() -> Unit),
    modifier: Modifier = Modifier,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onBack() }
            .padding(vertical = 8.dp),
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = LocalAppColors.current.textPrimary,
            modifier = Modifier.size(24.dp)
        )

        if (label != null) {
            Text(
                text = label,
                style = LocalAppTypography.current.body.copy(
                    fontWeight = FontWeight.Medium,
                    color = LocalAppColors.current.textPrimary
                ),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }

}
