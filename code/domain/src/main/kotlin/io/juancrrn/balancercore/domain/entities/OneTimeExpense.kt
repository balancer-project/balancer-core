package io.juancrrn.balancercore.domain.entities

import io.juancrrn.balancercore.domain.valueobjects.OneTimeExpenseStatus
import java.util.UUID

data class OneTimeExpense(
    val id: OneTimeExpenseId,
    val status: OneTimeExpenseStatus,
    val categoryId: ExpenseCategoryId,
    val tagsIds: List<ExpenseTagId>,
    val recipientId: ExpenseRecipientId,
    val concept: String,
    val comments: String,
    val amountType: ExpenseAmountType,
    val amount: Double,
    val paymentMethod: String, // TODO
    val hiddenInPlan: Boolean, // TODO
    val paymentId: ExpensePaymentId
)

typealias OneTimeExpenseId = UUID
