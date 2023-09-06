package io.juancrrn.balancercore.domain.entities

import io.juancrrn.balancercore.domain.valueobjects.TransactionId
import java.time.LocalDate
import java.util.UUID

/**
 * A registered payment of an expense.
 *
 * @property id Unique identifier.
 * @property finalAmount Amount of money paid.
 * @property postDate Date of the payment.
 * @property originTransactionsIds List of detected transactions that originated the payment.
 */
data class ExpensePayment(
    val id: ExpensePaymentId,
    val finalAmount: Float,
    val postDate: LocalDate,
    val originTransactionsIds: List<TransactionId>,
)

typealias ExpensePaymentId = UUID
