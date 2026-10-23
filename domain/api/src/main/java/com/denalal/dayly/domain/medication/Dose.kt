package com.denalal.dayly.domain.medication

data class Dose(
    val amount: Double,
    val unit: DoseUnit
) {
    init { require(amount > 0) { "Доза должна быть больше нуля" } }
}

enum class DoseUnit {
    MG, ML, TABLET, DROP
}
