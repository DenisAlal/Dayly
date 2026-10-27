package com.denalal.dayly.domain.medication.model

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertFailsWith

class MedicationScheduleTest {

    @Test
    fun `is active on listed day`() {
        val schedule = MedicationSchedule(
            times = listOf(MedicationTime(9, 0)),
            days = setOf(Weekday.MON, Weekday.WED),
        )

        assertTrue(schedule.isActiveOn(Weekday.MON))
        assertTrue(schedule.isActiveOn(Weekday.WED))
        assertFalse(schedule.isActiveOn(Weekday.TUE))
    }

    @Test
    fun `is every day when all weekdays are set`() {
        val everyDay = MedicationSchedule(listOf(MedicationTime(9, 0)))
        assertTrue(everyDay.isEveryDay())

        val weekdaysOnly = MedicationSchedule(
            times = listOf(MedicationTime(9, 0)),
            days = setOf(Weekday.MON, Weekday.TUE, Weekday.WED, Weekday.THU, Weekday.FRI),
        )
        assertFalse(weekdaysOnly.isEveryDay())
    }

    @Test
    fun `rejects empty times`() {
        assertFailsWith<IllegalArgumentException> {
            MedicationSchedule(times = emptyList())
        }
    }
}
