package io.juancrrn.balancercore.domain.entities

import java.util.*

data class ExpensePayment(
    val id: ExpensePaymentId
)

typealias ExpensePaymentId = UUID
