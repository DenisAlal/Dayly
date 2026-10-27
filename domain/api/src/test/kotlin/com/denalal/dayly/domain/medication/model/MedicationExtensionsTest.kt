package com.denalal.dayly.domain.medication.model

import kotlin.test.Test
import kotlin.test.assertEquals

class MedicationExtensionsTest {
    @Test
    fun `times sorted by hour and minute`() {
        val schedule = MedicationSchedule(
            times = listOf(MedicationTime(21, 0), MedicationTime(9, 30)),
        )

        assertEquals(
            listOf(MedicationTime(9, 30), MedicationTime(21, 0)),
            schedule.sortedTimes(),
        )
    }

}