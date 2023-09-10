package io.juancrrn.balancercore.domain.entities

import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentStatus
import io.juancrrn.balancercore.domain.valueobjects.TransactionId
import java.time.LocalDate
import java.util.UUID

/**
 * A payment of an expense.
 *
 * @property id Unique identifier.
 * @property expenseId The id of the expense to which the payment belongs.
 * @property status Status of the payment.
 * @property amount Amount of money paid.
 * @property date For pending transactions, the date when the transaction occurred; for posted transactions, the date that the transaction posted.
 * @property authorizationDate The date when the transaction was authorized. For posted transactions, the date field will indicate the posted date, but authorizationDate will indicate the day the transaction was authorized by the financial institution.
 * @property originTransactionsIds List of detected transactions that originated the payment.
 */
data class ExpensePayment(
    val id: ExpensePaymentId,
    val expenseId: ExpenseId,
    val status: ExpensePaymentStatus,
    val amount: Float,
    val date: LocalDate,
    val authorizationDate: LocalDate?,
    val originTransactionsIds: List<TransactionId>,
)

typealias ExpensePaymentId = UUID
