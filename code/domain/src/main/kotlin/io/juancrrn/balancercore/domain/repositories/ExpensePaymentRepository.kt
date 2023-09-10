package io.juancrrn.balancercore.domain.repositories

import io.juancrrn.balancercore.domain.entities.ExpensePayment
import io.juancrrn.balancercore.domain.entities.ExpensePaymentId

/**
 * Repository for expense payments.
 */
interface ExpensePaymentRepository {

    /**
     * Saves a payment.
     *
     * @param expensePayment The payment to save.
     */
    suspend fun save(expensePayment: ExpensePayment)

    /**
     * Finds a payment by its id.
     *
     * @param id The id of the payment.
     * @return The payment, or null if not found.
     */
    suspend fun find(id: ExpensePaymentId): ExpensePayment?
}