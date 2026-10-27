package com.denalal.dayly.domain.medication.model

fun List<Medication>.activeOnly(): List<Medication> = filter { it.isActive }

fun List<Medication>.allDoseTimes(): List<MedicationTime> =
    flatMap { it.schedule.times }
        .distinct()
        .sortedWith(compareBy({ it.hour }, { it.minute }))

fun List<Medication>.groupedByWeekday(): Map<Weekday, List<Medication>> =
    Weekday.entries.associateWith { day ->
        filter { day in it.schedule.days }
    }