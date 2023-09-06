package io.juancrrn.balancercore.domain.entities

import java.util.UUID

data class ExpensePayment(
    val id: ExpensePaymentId,
)

typealias ExpensePaymentId = UUID
