package com.denalal.dayly.feature.medications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.denalal.dayly.core.ui.component.DaylyButton
import com.denalal.dayly.core.ui.component.DaylyCard
import com.denalal.dayly.core.ui.component.DaylyPreview
import com.denalal.dayly.core.ui.theme.LocalSpacing
import com.denalal.dayly.domain.medication.model.Medication
import com.denalal.dayly.feature.medications.components.MedicationCard


@Composable
fun MedicationsScreen(
    modifier: Modifier = Modifier,
    medications: List<Medication> = MockMedications,
) {
    val spacing = LocalSpacing.current

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(spacing.lg),
        verticalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        if (medications.isNotEmpty())
            items(items = medications, key = { it.id }) { medication ->
                MedicationCard(medication = medication, modifier = Modifier.fillMaxWidth())
            } else {
            item(key = "Empty") {
                DaylyCard(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Пока нет лекарств", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(spacing.xs))
                    Text(
                        text = "Добавьте первое — напомним вовремя",
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        }
        item { DaylyButton("Добавить", onClick = {}) }
    }
}

@Preview(name = "Light", group = "MedicationScreen", showBackground = true)
@Composable
private fun MedicationScreenPreview() {
    DaylyPreview {
        MedicationsScreen()
    }
}

@Preview(name = "Dark", group = "MedicationScreen", showBackground = true)
@Composable
private fun MedicationScreenDarkPreview() {
    DaylyPreview(darkTheme = true) {
        MedicationsScreen()
    }
}

@Preview(name = "Light", group = "MedicationScreen", showBackground = true)
@Composable
private fun MedicationScreenEmptyPreview() {
    DaylyPreview {
        MedicationsScreen(medications = listOf())
    }
}

@Preview(name = "Dark", group = "MedicationScreen", showBackground = true)
@Composable
private fun MedicationScreenEmptyDarkPreview() {
    DaylyPreview(darkTheme = true) {
        MedicationsScreen(medications = listOf())
    }
}