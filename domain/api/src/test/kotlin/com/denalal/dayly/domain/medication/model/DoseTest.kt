package com.denalal.dayly.domain.medication.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DoseTest {

    @Test
    fun `formats whole amount without decimal part`() {
        assertEquals("1 таб.", Dose(1.0, DoseUnit.TABLET).format())
        assertEquals("500 мг.", Dose(500.0, DoseUnit.MG).format())
    }

    @Test
    fun `keeps fractional amount`() {
        assertEquals("0.5 мг.", Dose(0.5, DoseUnit.MG).format())
        assertEquals("1.25 мл.", Dose(1.25, DoseUnit.ML).format())
    }

    @Test
    fun `formats every unit`() {
        assertEquals("2 мг.", Dose(2.0, DoseUnit.MG).format())
        assertEquals("2 мл.", Dose(2.0, DoseUnit.ML).format())
        assertEquals("2 таб.", Dose(2.0, DoseUnit.TABLET).format())
        assertEquals("2 кап.", Dose(2.0, DoseUnit.DROP).format())
    }

    @Test
    fun `rejects non positive amount`() {
        assertFailsWith<IllegalArgumentException> { Dose(0.0, DoseUnit.MG) }
        assertFailsWith<IllegalArgumentException> { Dose(-1.0, DoseUnit.MG) }
    }
}
