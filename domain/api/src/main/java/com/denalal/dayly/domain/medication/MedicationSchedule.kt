package com.denalal.dayly.domain.medication

data class MedicationSchedule(
    val times: List<MedicationTime>,
    val days: Set<Weekday> = Weekday.entries.toSet()
) {
    init {
        require(times.isNotEmpty()) { "Нужно хотя бы одно время приема" }
    }
}
