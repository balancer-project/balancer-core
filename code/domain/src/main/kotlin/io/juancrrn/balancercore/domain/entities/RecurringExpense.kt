package io.juancrrn.balancercore.domain.entities

import io.juancrrn.balancercore.domain.valueobjects.ExpenseAmountType
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory
import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentMethod
import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseFrequency
import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseStatus
import io.juancrrn.balancercore.domain.valueobjects.UserId
import java.time.Instant
import java.time.LocalDate

/**
 * Represents an expense that is paid periodically.
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
 * @property frequency Frequency.
 * @property firstPaymentDate Date of the first payment.
 * @property lastPaymentDate Date of the last payment.
 * @property hiddenInPlans Whether the expense is hidden in plans.
 * @property paymentsIds List of payments identifiers.
 * @property createdAt Creation date.
 * @property updatedAt Last update date.
 * @property deletedAt Deletion date.
 */
class RecurringExpense(
    override val id: ExpenseId,
    override val userId: UserId,
    val status: RecurringExpenseStatus,
    override val category: ExpenseCategory,
    override val recipientId: ExpenseRecipientId,
    override val concept: String,
    override val comments: String,
    override val amount: Float,
    override val amountType: ExpenseAmountType,
    override val paymentMethod: ExpensePaymentMethod,
    val frequency: RecurringExpenseFrequency,
    val firstPaymentDate: LocalDate,
    val lastPaymentDate: LocalDate?,
    override val hiddenInPlans: Boolean,
    val paymentsIds: List<ExpensePaymentId>,
    override val createdAt: Instant,
    override val updatedAt: Instant,
    override val deletedAt: Instant?,
) : Expense
