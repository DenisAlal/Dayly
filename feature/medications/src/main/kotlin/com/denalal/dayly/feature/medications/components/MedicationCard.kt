package com.denalal.dayly.feature.medications.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.denalal.dayly.core.ui.component.DaylyCard
import com.denalal.dayly.core.ui.component.DaylyPreview
import com.denalal.dayly.core.ui.theme.LocalSpacing
import com.denalal.dayly.domain.medication.model.Medication
import com.denalal.dayly.domain.medication.model.format
import com.denalal.dayly.domain.medication.model.sortedTimes
import com.denalal.dayly.feature.medications.MockMedications

@Composable
fun MedicationCard(
    medication: Medication,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    val spacing = LocalSpacing.current

    DaylyCard(modifier = modifier.fillMaxWidth(), onClick = onClick) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = medication.name,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = medication.dose.format(),
            )
        }
        Spacer(Modifier.height(spacing.sm))
        Text(
            text = medication.schedule.sortedTimes().joinToString(" · ") { it.format() },
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Preview(name = "Light", group = "MedicationCard", showBackground = true)
@Composable
private fun MedicationCardPreview() {
    DaylyPreview {
        MedicationCard(
            medication = MockMedications.first(),
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {}
        )
    }
}

@Preview(name = "Dark", group = "MedicationCard", showBackground = true)
@Composable
private fun MedicationCardDarkPreview() {
    DaylyPreview(darkTheme = true) {
        MedicationCard(
            medication = MockMedications.first(),
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {}
        )
    }
}