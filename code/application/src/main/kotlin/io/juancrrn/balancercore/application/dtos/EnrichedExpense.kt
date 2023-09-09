package io.juancrrn.balancercore.application.dtos

import io.juancrrn.balancercore.domain.entities.ExpenseId
import io.juancrrn.balancercore.domain.entities.ExpenseRecipient
import io.juancrrn.balancercore.domain.valueobjects.ExpenseAmountType
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory
import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentMethod
import io.juancrrn.balancercore.domain.valueobjects.UserId
import java.time.Instant

sealed interface EnrichedExpense {

    val id: ExpenseId
    val userId: UserId
    val category: ExpenseCategory
    val recipient: ExpenseRecipient
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
