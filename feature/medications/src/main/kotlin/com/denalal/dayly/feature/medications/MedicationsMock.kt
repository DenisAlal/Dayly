package com.denalal.dayly.feature.medications

import com.denalal.dayly.domain.medication.model.Dose
import com.denalal.dayly.domain.medication.model.DoseUnit
import com.denalal.dayly.domain.medication.model.Medication
import com.denalal.dayly.domain.medication.model.MedicationSchedule
import com.denalal.dayly.domain.medication.model.MedicationTime
import com.denalal.dayly.domain.medication.model.Weekday

val MockMedications: List<Medication> = listOf(
    Medication(
        id = "1",
        name = "Витамин D",
        dose = Dose(1.0, DoseUnit.TABLET),
        schedule = MedicationSchedule(
            times = listOf(MedicationTime(9, 0)),
            days = setOf(Weekday.MON, Weekday.TUE, Weekday.WED, Weekday.THU, Weekday.FRI)
        )
    ),
    Medication(
        id = "2",
        name = "Витамин A",
        dose = Dose(2.0, DoseUnit.TABLET),
        schedule = MedicationSchedule(
            times = listOf(MedicationTime(14, 0)),
        )
    ),
    Medication(
        id = "3",
        name = "Витамин C",
        dose = Dose(1.0, DoseUnit.TABLET),
        schedule = MedicationSchedule(
            times = listOf(MedicationTime(9, 0)),
            days = setOf(Weekday.MON, Weekday.FRI)
        )
    ),
    Medication(
        id = "4",
        name = "Аспирин",
        dose = Dose(125.0, DoseUnit.MG),
        schedule = MedicationSchedule(
            times = listOf(MedicationTime(9, 0), MedicationTime(10, 0)),
        )
    )
)