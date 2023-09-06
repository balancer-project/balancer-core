package io.juancrrn.balancercore.domain.repositories

import io.juancrrn.balancercore.domain.entities.Expense
import io.juancrrn.balancercore.domain.valueobjects.UserId

/**
 * Repository of expenses.
 */
interface ExpenseRepository {

    /**
     * Saves an expense.
     *
     * @param expense The expense to save.
     */
    suspend fun save(expense: Expense)

    /**
     * Finds all the expenses of a user.
     *
     * @param userId The user's identifier.
     * @return A list of expenses.
     */
    suspend fun find(userId: UserId): List<Expense>
}
