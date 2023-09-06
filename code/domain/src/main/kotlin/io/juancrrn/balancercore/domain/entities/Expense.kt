package io.juancrrn.balancercore.domain.entities

import io.juancrrn.balancercore.domain.valueobjects.ExpenseAmountType
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory
import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentMethod
import io.juancrrn.balancercore.domain.valueobjects.UserId
import java.time.Instant
import java.util.UUID

/**
 * An expense.
 *
 * @property id Unique identifier.
 * @property userId Identifier of the user who registered the expense.
 * @property category Category.
 * @property recipientId Recipient identifier.
 * @property concept Concept.
 * @property comments Comments.
 * @property amount Amount.
 * @property amountType Amount type.
 * @property paymentMethod Payment method.
 * @property hiddenInPlans Whether the expense is hidden in plans.
 * @property createdAt Creation date.
 * @property updatedAt Last update date.
 * @property deletedAt Deletion date.
 */
sealed interface Expense {

    val id: ExpenseId
    val userId: UserId
    val category: ExpenseCategory
    val recipientId: ExpenseRecipientId
    val concept: String
    val comments: String
    val amount: Float
    val amountType: ExpenseAmountType
    val paymentMethod: ExpensePaymentMethod
    val hiddenInPlans: Boolean
    val createdAt: Instant
    val updatedAt: Instant
    val deletedAt: Instant?
}

typealias ExpenseId = UUID
