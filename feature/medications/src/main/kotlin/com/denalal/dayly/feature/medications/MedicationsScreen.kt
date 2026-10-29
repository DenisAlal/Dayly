package com.denalal.dayly.feature.medications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.denalal.dayly.core.ui.component.DaylyButton
import com.denalal.dayly.core.ui.component.DaylyCard
import com.denalal.dayly.core.ui.component.DaylyInput
import com.denalal.dayly.core.ui.component.DaylyPreview
import com.denalal.dayly.core.ui.theme.LocalSpacing
import com.denalal.dayly.domain.medication.MockMedications
import com.denalal.dayly.domain.medication.model.DoseUnit
import com.denalal.dayly.feature.medications.components.MedicationCard

@Composable
fun MedicationsScreen(
    modifier: Modifier = Modifier,
    viewModel: MedicationsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MedicationsContent(
        state = state,
        modifier = modifier,
        onAdd = { name ->
            viewModel.addMedication(
                name = name,
                doseAmount = 1.0,
                doseUnit = DoseUnit.TABLET,
                hour = 9,
                minute = 0,
            )
        },
    )
}

@Composable
fun MedicationsContent(
    state: MedicationsUiState,
    modifier: Modifier = Modifier,
    onAdd: (String) -> Unit = {},
) {
    val spacing = LocalSpacing.current
    var inputData by rememberSaveable { mutableStateOf("") }
    when (state) {
        MedicationsUiState.Loading -> Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }

        MedicationsUiState.Empty -> Box(
            modifier = modifier
                .fillMaxSize()
                .padding(spacing.lg),
            contentAlignment = Alignment.Center,
        ) {
            DaylyCard(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Пока нет лекарств", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(spacing.xs))
                Text(
                    text = "Добавьте первое — напомним вовремя",
                    style = MaterialTheme.typography.bodySmall,
                )
                Spacer(Modifier.height(spacing.sm))
                DaylyButton("Добавить", onClick = { onAdd(inputData) })
            }
        }

        is MedicationsUiState.Content -> LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(spacing.lg),
            verticalArrangement = Arrangement.spacedBy(spacing.sm),
        ) {
            items(items = state.medications, key = { it.id }) { medication ->
                MedicationCard(medication = medication, modifier = Modifier.fillMaxWidth())
            }
            item {
                DaylyInput(
                    value = inputData,
                    onValueChange = { inputData = it },
                    label = "Input"
                )
            }
            item {
                DaylyButton("Добавить", onClick = { onAdd(inputData) })
            }
        }

        is MedicationsUiState.Error -> Box(
            modifier = modifier
                .fillMaxSize()
                .padding(spacing.lg),
            contentAlignment = Alignment.Center,
        ) {
            DaylyCard(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(state.message)
                Spacer(Modifier.height(spacing.sm))
                DaylyButton("Повторить", onClick = {})
            }
        }
    }
}

@Preview(name = "Light", group = "MedicationScreen", showBackground = true)
@Composable
private fun MedicationScreenPreview() {
    DaylyPreview {
        MedicationsContent(state = MedicationsUiState.Content(MockMedications))
    }
}

@Preview(name = "Dark", group = "MedicationScreen", showBackground = true)
@Composable
private fun MedicationScreenDarkPreview() {
    DaylyPreview(darkTheme = true) {
        MedicationsContent(state = MedicationsUiState.Content(MockMedications))
    }
}

@Preview(name = "Light", group = "MedicationScreen", showBackground = true)
@Composable
private fun MedicationScreenEmptyPreview() {
    DaylyPreview {
        MedicationsContent(state = MedicationsUiState.Empty)
    }
}

@Preview(name = "Dark", group = "MedicationScreen", showBackground = true)
@Composable
private fun MedicationScreenEmptyDarkPreview() {
    DaylyPreview(darkTheme = true) {
        MedicationsContent(state = MedicationsUiState.Empty)
    }
}

@Preview(name = "Light", group = "MedicationScreen", showBackground = true)
@Composable
private fun MedicationScreenLoadingPreview() {
    DaylyPreview {
        MedicationsContent(state = MedicationsUiState.Loading)
    }
}

@Preview(name = "Dark", group = "MedicationScreen", showBackground = true)
@Composable
private fun MedicationScreenLoadingDarkPreview() {
    DaylyPreview(darkTheme = true) {
        MedicationsContent(state = MedicationsUiState.Loading)
    }
}

@Preview(name = "Light", group = "MedicationScreen", showBackground = true)
@Composable
private fun MedicationScreenErrorPreview() {
    DaylyPreview {
        MedicationsContent(state = MedicationsUiState.Error("Не удалось загрузить данные"))
    }
}

@Preview(name = "Dark", group = "MedicationScreen", showBackground = true)
@Composable
private fun MedicationScreenErrorDarkPreview() {
    DaylyPreview(darkTheme = true) {
        MedicationsContent(state = MedicationsUiState.Error("Не удалось загрузить данные"))
    }
}
