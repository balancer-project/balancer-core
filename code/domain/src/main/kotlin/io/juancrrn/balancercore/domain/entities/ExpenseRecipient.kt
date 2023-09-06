package io.juancrrn.balancercore.domain.entities

import java.util.UUID

/**
 * The recipient of an expense.
 *
 * @property id Unique identifier.
 */
data class ExpenseRecipient(
    val id: ExpenseRecipientId,
)

typealias ExpenseRecipientId = UUID
