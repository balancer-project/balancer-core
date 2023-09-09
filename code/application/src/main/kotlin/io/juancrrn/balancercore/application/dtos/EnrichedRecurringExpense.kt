package io.juancrrn.balancercore.application.dtos

import io.juancrrn.balancercore.domain.entities.ExpenseId
import io.juancrrn.balancercore.domain.entities.ExpensePayment
import io.juancrrn.balancercore.domain.entities.ExpenseRecipient
import io.juancrrn.balancercore.domain.entities.RecurringExpense
import io.juancrrn.balancercore.domain.valueobjects.ExpenseAmountType
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory
import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentMethod
import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseFrequency
import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseStatus
import io.juancrrn.balancercore.domain.valueobjects.UserId
import java.time.Instant
import java.time.LocalDate

class EnrichedRecurringExpense(
    override val id: ExpenseId,
    override val userId: UserId,
    val status: RecurringExpenseStatus,
    override val category: ExpenseCategory,
    override val recipient: ExpenseRecipient,
    override val concept: String,
    override val comments: String,
    override val amount: Float,
    override val amountType: ExpenseAmountType,
    override val paymentMethod: ExpensePaymentMethod,
    val frequency: RecurringExpenseFrequency,
    val firstPaymentDate: LocalDate,
    val lastPaymentDate: LocalDate?,
    override val hiddenInPlans: Boolean,
    val payments: List<ExpensePayment>,
    override val createdAt: Instant,
    override val updatedAt: Instant,
    override val deletedAt: Instant?,
) : EnrichedExpense {

    companion object {

        fun from(
            expense: RecurringExpense,
            recipient: ExpenseRecipient,
            payments: List<ExpensePayment>,
        ): EnrichedRecurringExpense = expense.run {
            return EnrichedRecurringExpense(
                id = id,
                userId = userId,
                status = status,
                category = category,
                recipient = recipient,
                concept = concept,
                comments = comments,
                amount = amount,
                amountType = amountType,
                paymentMethod = paymentMethod,
                frequency = frequency,
                firstPaymentDate = firstPaymentDate,
                lastPaymentDate = lastPaymentDate,
                hiddenInPlans = hiddenInPlans,
                payments = payments,
                createdAt = createdAt,
                updatedAt = updatedAt,
                deletedAt = deletedAt,
            )
        }
    }
}
