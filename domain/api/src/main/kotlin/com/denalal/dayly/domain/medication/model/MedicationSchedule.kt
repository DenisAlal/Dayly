package com.denalal.dayly.domain.medication.model

data class MedicationSchedule(
    val times: List<MedicationTime>,
    val days: Set<Weekday> = Weekday.entries.toSet()
) {
    init {
        require(times.isNotEmpty()) { "Нужно хотя бы одно время приема" }
    }
}

fun MedicationSchedule.sortedTimes(): List<MedicationTime> =
    times.sortedWith(compareBy({ it.hour }, { it.minute }))

fun MedicationSchedule.isActiveOn(day: Weekday): Boolean = day in days

fun MedicationSchedule.isEveryDay(): Boolean = days.size == Weekday.entries.size