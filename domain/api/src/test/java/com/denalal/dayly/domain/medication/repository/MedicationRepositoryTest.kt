package com.denalal.dayly.domain.medication.repository

import com.denalal.dayly.domain.medication.model.Dose
import com.denalal.dayly.domain.medication.model.DoseUnit
import com.denalal.dayly.domain.medication.model.Medication
import com.denalal.dayly.domain.medication.model.MedicationSchedule
import com.denalal.dayly.domain.medication.model.MedicationTime
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MedicationRepositoryTest {

    @Test
    fun `starts with empty list`() = runTest {
        val repository = FakeMedicationRepository()

        assertEquals(emptyList(), repository.observeAll().first())
    }

    @Test
    fun `emits added medication`() = runTest {
        val repository = FakeMedicationRepository()
        val vitaminD = medication("1")

        repository.add(vitaminD)

        assertEquals(listOf(vitaminD), repository.observeAll().first())
    }

    @Test
    fun `keeps insertion order`() = runTest {
        val repository = FakeMedicationRepository()
        val vitaminD = medication("1")
        val omega3 = medication("2")

        repository.add(vitaminD)
        repository.add(omega3)

        assertEquals(listOf(vitaminD, omega3), repository.observeAll().first())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `emits every change with size`() = runTest {
        val repository = FakeMedicationRepository()
        val sizes = mutableListOf<Int>()

        val collector = launch(UnconfinedTestDispatcher(testScheduler)) {
            repository.observeAll()
                .map { it.size }
                .take(3)
                .toList(sizes)
        }

        repository.add(medication("1"))
        repository.add(medication("2"))
        collector.join()

        assertEquals(listOf(0, 1, 2), sizes)
    }

    @Test
    fun `mark as taken records id`() = runTest {
        val repository = FakeMedicationRepository()

        repository.markAsTaken("42")

        assertEquals(listOf("42"), repository.takenIds)
    }
}

private fun medication(id: String) = Medication(
    id = id,
    name = "Тест $id",
    dose = Dose(1.0, DoseUnit.TABLET),
    schedule = MedicationSchedule(listOf(MedicationTime(9, 0))),
)
