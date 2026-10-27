package com.denalal.dayly.domain.medication.model

data class Dose(
    val amount: Double,
    val unit: DoseUnit
) {
    init {
        require(amount > 0) { "Доза должна быть больше нуля" }
    }
}

fun Dose.format(): String {
    val unitLabel = when (unit) {
        DoseUnit.MG -> "мг."
        DoseUnit.ML -> "мл."
        DoseUnit.TABLET -> "таб."
        DoseUnit.DROP -> "кап."
    }

    val amountLabel = if (amount % 1.0 == 0.0) amount.toInt().toString() else amount.toString()
    return "$amountLabel $unitLabel"
}

enum class DoseUnit {
    MG, ML, TABLET, DROP
}
