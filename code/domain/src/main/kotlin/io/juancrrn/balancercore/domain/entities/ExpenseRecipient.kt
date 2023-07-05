package io.juancrrn.balancercore.domain.entities

import java.util.*

data class ExpenseRecipient(
    val id: ExpenseRecipientId
)

typealias ExpenseRecipientId = UUID
