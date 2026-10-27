package com.denalal.dayly.domain.medication.model

data class MedicationTime(val hour: Int, val minute: Int) {
    init {
        require(hour in 0..23) { "hour должен быть 0..23, был: $hour" }
        require(minute in 0..59) { "minute должен быть 0..59, был: $minute" }
    }
}

fun MedicationTime.format(): String = "%02d:%02d".format(hour, minute)
