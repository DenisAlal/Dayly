package com.denalal.dayly.domain.medication

data class Medication(
    val id: String,
    val name: String,
    val dose: Dose,
    val schedule: MedicationSchedule,
    val isActive: Boolean = true
)
