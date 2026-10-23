package com.denalal.dayly.domain.medication.model

import kotlin.test.Test
import kotlin.test.assertEquals

class MedicationListExtensionsTest {

    @Test
    fun `active only keeps active medications`() {
        val active = medication(id = "1")
        val inactive = medication(id = "2", isActive = false)

        val result = listOf(active, inactive).activeOnly()

        assertEquals(listOf("1"), result.map { it.id })
    }

    @Test
    fun `all dose times are distinct and sorted`() {
        val list = listOf(
            medication(
                id = "1",
                times = listOf(MedicationTime(21, 0), MedicationTime(9, 0)),
            ),
            medication(
                id = "2",
                times = listOf(MedicationTime(9, 0)),
            ),
        )

        assertEquals(
            listOf(MedicationTime(9, 0), MedicationTime(21, 0)),
            list.allDoseTimes(),
        )
    }

    @Test
    fun `grouped by weekday includes only matching medications`() {
        val monday = medication(id = "1", days = setOf(Weekday.MON))
        val sunday = medication(id = "2", days = setOf(Weekday.SUN))

        val grouped = listOf(monday, sunday).groupedByWeekday()

        assertEquals(listOf("1"), grouped.getValue(Weekday.MON).map { it.id })
        assertEquals(listOf("2"), grouped.getValue(Weekday.SUN).map { it.id })
        assertEquals(emptyList<String>(), grouped.getValue(Weekday.TUE).map { it.id })
    }

    private fun medication(
        id: String,
        times: List<MedicationTime> = listOf(MedicationTime(9, 0)),
        days: Set<Weekday> = Weekday.entries.toSet(),
        isActive: Boolean = true,
    ) = Medication(
        id = id,
        name = "Тестовое лекарство $id",
        dose = Dose(1.0, DoseUnit.TABLET),
        schedule = MedicationSchedule(times = times, days = days),
        isActive = isActive,
    )
}
