package com.denalal.dayly.domain.medication.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MedicationTimeTest {

    @Test
    fun `formats with leading zeros`() {
        assertEquals("09:05", MedicationTime(9, 5).format())
        assertEquals("00:00", MedicationTime(0, 0).format())
        assertEquals("23:59", MedicationTime(23, 59).format())
    }

    @Test
    fun `rejects hour outside 0 to 23`() {
        assertFailsWith<IllegalArgumentException> { MedicationTime(24, 0) }
        assertFailsWith<IllegalArgumentException> { MedicationTime(-1, 0) }
    }

    @Test
    fun `rejects minute outside 0 to 59`() {
        assertFailsWith<IllegalArgumentException> { MedicationTime(10, 60) }
        assertFailsWith<IllegalArgumentException> { MedicationTime(10, -1) }
    }
}
