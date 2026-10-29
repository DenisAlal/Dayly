package com.denalal.dayly.feature.medications

import com.denalal.dayly.domain.medication.model.Medication

sealed interface MedicationsUiState {
    data object Loading : MedicationsUiState
    data object Empty : MedicationsUiState
    data class Content(val medications: List<Medication>) : MedicationsUiState
    data class Error(val message: String) : MedicationsUiState

}