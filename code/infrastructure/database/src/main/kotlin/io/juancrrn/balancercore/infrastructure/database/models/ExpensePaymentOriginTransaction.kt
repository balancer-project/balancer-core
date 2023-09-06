package io.juancrrn.balancercore.infrastructure.database.models

import java.util.UUID

data class ExpensePaymentOriginTransaction(
    val paymentId: UUID,
    val transactionId: String,
)
