package io.juancrrn.balancercore.domain.repositories

import io.juancrrn.balancercore.domain.entities.ExpenseRecipient
import io.juancrrn.balancercore.domain.entities.ExpenseRecipientId

/**
 * Repository for expense recipients.
 */
interface ExpenseRecipientRepository {

    /**
     * Finds a recipient by its id.
     *
     * @param id The id of the recipient.
     * @return The recipient, or null if not found.
     */
    suspend fun find(id: ExpenseRecipientId): ExpenseRecipient?
}
