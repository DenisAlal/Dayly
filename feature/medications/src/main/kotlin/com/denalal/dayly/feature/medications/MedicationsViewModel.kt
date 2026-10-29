package com.denalal.dayly.feature.medications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denalal.dayly.domain.medication.model.Dose
import com.denalal.dayly.domain.medication.model.DoseUnit
import com.denalal.dayly.domain.medication.model.Medication
import com.denalal.dayly.domain.medication.model.MedicationSchedule
import com.denalal.dayly.domain.medication.model.MedicationTime
import com.denalal.dayly.domain.medication.model.Weekday
import com.denalal.dayly.domain.medication.repository.MedicationRepository
import java.util.UUID
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
@HiltViewModel
class MedicationsViewModel @Inject constructor(
    private val repository: MedicationRepository
) : ViewModel() {
    private val _state = MutableStateFlow<MedicationsUiState>(MedicationsUiState.Loading)
    val state: StateFlow<MedicationsUiState> = _state.asStateFlow()

    fun addMedication(medication: Medication) {
        viewModelScope.launch {
            repository.add(medication)
        }
    }

    fun addMedication(
        name: String,
        doseAmount: Double,
        doseUnit: DoseUnit,
        hour: Int,
        minute: Int,
        days: Set<Weekday> = Weekday.entries.toSet(),
    ) {
        val medication = Medication(
            id = UUID.randomUUID().toString(),
            name = name,
            dose = Dose(doseAmount, doseUnit),
            schedule = MedicationSchedule(
                times = listOf(MedicationTime(hour, minute)),
                days = days,
            ),
        )
        addMedication(medication)
    }

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            repository.observeAll().collect { medications ->
                _state.value = if (medications.isEmpty()) {
                    MedicationsUiState.Empty
                } else {
                    MedicationsUiState.Content(medications)
                }
            }
        }
    }
}