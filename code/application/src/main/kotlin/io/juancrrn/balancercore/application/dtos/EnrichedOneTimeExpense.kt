package io.juancrrn.balancercore.application.dtos

import io.juancrrn.balancercore.domain.entities.ExpenseId
import io.juancrrn.balancercore.domain.entities.ExpensePayment
import io.juancrrn.balancercore.domain.entities.ExpenseRecipient
import io.juancrrn.balancercore.domain.entities.OneTimeExpense
import io.juancrrn.balancercore.domain.valueobjects.ExpenseAmountType
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory
import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentMethod
import io.juancrrn.balancercore.domain.valueobjects.OneTimeExpenseStatus
import io.juancrrn.balancercore.domain.valueobjects.UserId
import java.time.Instant

class EnrichedOneTimeExpense(
    override val id: ExpenseId,
    override val userId: UserId,
    val status: OneTimeExpenseStatus,
    override val category: ExpenseCategory,
    override val recipient: ExpenseRecipient,
    override val concept: String,
    override val comments: String,
    override val amount: Float,
    override val amountType: ExpenseAmountType,
    override val paymentMethod: ExpensePaymentMethod,
    override val hiddenInPlans: Boolean,
    val payment: ExpensePayment?,
    override val createdAt: Instant,
    override val updatedAt: Instant,
    override val deletedAt: Instant?,
) : EnrichedExpense {

    companion object {

        fun from(
            expense: OneTimeExpense,
            recipient: ExpenseRecipient,
            payment: ExpensePayment?,
        ): EnrichedOneTimeExpense = expense.run {
            return EnrichedOneTimeExpense(
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
                hiddenInPlans = hiddenInPlans,
                payment = payment,
                createdAt = createdAt,
                updatedAt = updatedAt,
                deletedAt = deletedAt,
            )
        }
    }
}
