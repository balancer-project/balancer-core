package io.juancrrn.balancercore.domain.entities

import java.util.UUID

data class ExpenseRecipient(
    val id: ExpenseRecipientId,
)

typealias ExpenseRecipientId = UUID
