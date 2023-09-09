package io.juancrrn.balancercore.domain.entities

import java.util.UUID

/**
 * The recipient of an expense.
 *
 * @property id Unique identifier.
 * @property name Name of the recipient.
 */
data class ExpenseRecipient(
    val id: ExpenseRecipientId,
    val name: String,
)

typealias ExpenseRecipientId = UUID
