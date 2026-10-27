package com.denalal.dayly.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.denalal.dayly.core.ui.theme.DaylyTheme
import com.denalal.dayly.core.ui.theme.LocalDaylyDisabledColors


@Composable
fun DaylyButton(text: String, onClick: () -> Unit, modifier: Modifier, enabled: Boolean = true) {
    val disabled = LocalDaylyDisabledColors.current

    Button(
        onClick = onClick,
        enabled = enabled,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = disabled.container,
            disabledContentColor = disabled.content,
        ),
        contentPadding = PaddingValues(),
        modifier = modifier
            .clip(RoundedCornerShape(18.dp)),
    ) {
        Text(text, style = MaterialTheme.typography.labelLarge)
    }
}

@Preview(name = "Light", group = "Button", showBackground = true)
@Composable
private fun DaylyButtonStatesPreview() {
    DaylyTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            DaylyButton("Сохранить", onClick = {}, modifier = Modifier.fillMaxWidth())
            DaylyButton("Сохранить", onClick = {}, modifier = Modifier.fillMaxWidth(), enabled = false)
        }
    }
}

@Preview(name = "Dark", group = "Button", showBackground = true)
@Composable
private fun DaylyButtonStatesDarkPreview() {
    DaylyTheme(darkTheme = true) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            DaylyButton("Сохранить", onClick = {}, modifier = Modifier.fillMaxWidth())
            DaylyButton("Сохранить", onClick = {}, modifier = Modifier.fillMaxWidth(), enabled = false)
        }
    }
}