package io.juancrrn.balancercore.domain.entities

import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentStatus
import io.juancrrn.balancercore.domain.valueobjects.TransactionId
import java.time.LocalDate
import java.util.UUID

/**
 * A payment of an expense.
 *
 * @property id Unique identifier.
 * @property status Status of the payment.
 * @property amount Amount of money paid.
 * @property authorizationDate Date of the authorization of the payment.
 * @property postDate Date when the payment was posted.
 * @property originTransactionsIds List of detected transactions that originated the payment.
 */
data class ExpensePayment(
    val id: ExpensePaymentId,
    val status: ExpensePaymentStatus,
    val amount: Float,
    val authorizationDate: LocalDate,
    val postDate: LocalDate?,
    val originTransactionsIds: List<TransactionId>,
)

typealias ExpensePaymentId = UUID
