package com.denalal.dayly.core.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.denalal.dayly.core.ui.theme.LocalSpacing

@Composable
fun DaylyCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val spacing = LocalSpacing.current

    val colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    )
    val shape = MaterialTheme.shapes.large
    val border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)

    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = modifier,
            shape = shape,
            colors = colors,
            border = border,
        ) {
            Column(Modifier.padding(spacing.lg)) {
                content()
            }
        }
    } else {
        Card(
            modifier = modifier,
            shape = shape,
            colors = colors,
            border = border,
        ) {
            Column(Modifier.padding(spacing.lg)) {
                content()
            }
        }
    }
}

@Preview(name = "Light", group = "Card", showBackground = true)
@Composable
private fun DaylyCardPreview() {
    DaylyPreview {
        DaylyCard(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Аскорбинка",
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "1 таб.",
                )
            }
            Spacer(Modifier.height(LocalSpacing.current.sm))
            Text(
                text = "09:00 · 21:00",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview(name = "Dark", group = "Card", showBackground = true)
@Composable
private fun DaylyCardDarkPreview() {
    DaylyPreview(darkTheme = true) {
        DaylyCard(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Аскорбинка",
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "1 таб.",
                )
            }
            Spacer(Modifier.height(LocalSpacing.current.sm))
            Text(
                text = "09:00 · 21:00",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}