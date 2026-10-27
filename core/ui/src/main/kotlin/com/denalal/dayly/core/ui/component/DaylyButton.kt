package com.denalal.dayly.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.denalal.dayly.core.ui.theme.LocalDaylyDisabledColors


@Composable
fun DaylyButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true) {
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
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
        modifier = modifier,
    ) {
        Text(text, style = MaterialTheme.typography.labelLarge)
    }
}

@Preview(name = "Light", group = "Button", showBackground = true)
@Composable
private fun DaylyButtonStatesPreview() {
    DaylyPreview {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DaylyButton(
                text = "Сохранить",
                onClick = {},
                modifier = Modifier
                    .widthIn(max = 320.dp)
                    .fillMaxWidth(),
            )
            DaylyButton(
                text = "Недоступно",
                onClick = {},
                modifier = Modifier
                    .widthIn(max = 320.dp)
                    .fillMaxWidth(),
                enabled = false,
            )
        }
    }
}

@Preview(name = "Dark", group = "Button", showBackground = true)
@Composable
private fun DaylyButtonStatesDarkPreview() {
    DaylyPreview(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DaylyButton(
                text = "Сохранить",
                onClick = {},
                modifier = Modifier
                    .widthIn(max = 320.dp)
                    .fillMaxWidth(),
            )
            DaylyButton(
                text = "Недоступно",
                onClick = {},
                modifier = Modifier
                    .widthIn(max = 320.dp)
                    .fillMaxWidth(),
                enabled = false,
            )
        }
    }
}