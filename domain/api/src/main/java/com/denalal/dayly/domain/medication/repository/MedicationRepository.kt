package com.denalal.dayly.domain.medication.repository

import com.denalal.dayly.domain.medication.model.Medication
import kotlinx.coroutines.flow.Flow

interface MedicationRepository {
    fun observeAll(): Flow<List<Medication>>
    suspend fun add(medication: Medication)
    suspend fun markAsTaken(id: String)
}