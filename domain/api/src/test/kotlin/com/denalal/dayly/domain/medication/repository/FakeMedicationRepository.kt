package com.denalal.dayly.domain.medication.repository

import com.denalal.dayly.domain.medication.model.Medication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeMedicationRepository @Inject constructor() : MedicationRepository {
    private val state = MutableStateFlow<List<Medication>>(emptyList())
    private val taken = mutableListOf<String>()

    val takenIds: List<String> get() = taken.toList()

    override fun observeAll(): Flow<List<Medication>> = state.asStateFlow()
    override suspend fun add(medication: Medication) { state.update { it + medication } }
    override suspend fun markAsTaken(id: String) { taken += id }
}