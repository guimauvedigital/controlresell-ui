package com.controlresell.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Bold
import com.adamglin.phosphoricons.bold.ArrowLeft
import org.jetbrains.compose.resources.stringResource

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
            imageVector = PhosphorIcons.Bold.ArrowLeft,
            contentDescription = stringResource(Res.string.back_button_description),
            tint = LocalColorScheme.current.onBackground,
            modifier = Modifier.size(24.dp)
        )

        if (label != null) Text(
            text = label,
            style = LocalTypography.current.p.copy(
                fontWeight = FontWeight.Medium,
            ),
            modifier = Modifier.padding(start = 8.dp)
        )
    }

}
