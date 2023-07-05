package io.juancrrn.balancercore.domain.entities

import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseStatus
import java.util.*

data class RecurringExpense(
    val id: RecurringExpenseId,
    val status: RecurringExpenseStatus,
    val categoryId: ExpenseCategoryId,
    val tagsIds: List<ExpenseTagId>,
    val recipientId: ExpenseRecipientId,
    val concept: String,
    val comments: String,
    val amountType: ExpenseAmountType,
    val amount: Double,
    val paymentMethod: String, // TODO
    val hiddenInPlan: Boolean, // TODO
    val paymentsIds: List<ExpensePaymentId>
)

typealias RecurringExpenseId = UUID
