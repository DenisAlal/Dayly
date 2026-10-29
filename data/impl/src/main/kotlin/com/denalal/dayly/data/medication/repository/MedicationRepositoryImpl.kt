package com.denalal.dayly.data.medication.repository

import com.denalal.dayly.domain.medication.MockMedications
import com.denalal.dayly.domain.medication.model.Medication
import com.denalal.dayly.domain.medication.repository.MedicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicationRepositoryImpl @Inject constructor() : MedicationRepository {

    private val medications = MutableStateFlow<List<Medication>>(MockMedications)

    override fun observeAll(): Flow<List<Medication>> {
        return medications.asStateFlow()
    }

    override suspend fun add(medication: Medication) {
        medications.value += medication
    }

    override suspend fun markAsTaken(id: String) {
        // Реализуешь после того, как определишь модель Medication.
    }
}