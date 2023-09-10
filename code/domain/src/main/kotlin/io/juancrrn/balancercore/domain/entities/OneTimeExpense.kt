package io.juancrrn.balancercore.domain.entities

import io.juancrrn.balancercore.domain.valueobjects.ExpenseAmountType
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory
import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentMethod
import io.juancrrn.balancercore.domain.valueobjects.OneTimeExpenseStatus
import io.juancrrn.balancercore.domain.valueobjects.UserId
import java.time.Instant

/**
 * An expense that is not repeated.
 *
 * @property id Unique identifier.
 * @property userId Identifier of the user who registered the expense.
 * @property status Status.
 * @property category Category.
 * @property recipientId Recipient identifier.
 * @property concept Concept.
 * @property comments Comments.
 * @property amount Amount.
 * @property amountType Amount type.
 * @property paymentMethod Payment method.
 * @property hiddenInPlans Whether the expense is hidden in plans.
 * @property paymentId Payment identifier.
 * @property createdAt Creation date.
 * @property updatedAt Last update date.
 * @property deletedAt Deletion date.
 */
data class OneTimeExpense(
    override val id: ExpenseId,
    override val userId: UserId,
    val status: OneTimeExpenseStatus,
    override val category: ExpenseCategory,
    override val recipientId: ExpenseRecipientId,
    override val concept: String,
    override val comments: String,
    override val amount: Float,
    override val amountType: ExpenseAmountType,
    override val paymentMethod: ExpensePaymentMethod,
    override val hiddenInPlans: Boolean,
    val paymentId: ExpensePaymentId?,
    override val createdAt: Instant,
    override val updatedAt: Instant,
    override val deletedAt: Instant? = null,
) : Expense
