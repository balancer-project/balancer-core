package io.juancrrn.balancercore.restserver.models.ext

import io.juancrrn.balancercore.domain.entities.OneTimeExpense
import io.juancrrn.balancercore.domain.entities.RecurringExpense
import io.juancrrn.balancercore.restserver.api.models.Expense
import io.juancrrn.balancercore.restserver.api.models.ExpenseType
import io.juancrrn.balancercore.domain.entities.Expense as ExpenseEntity

fun ExpenseEntity.toModel(): Expense {
    return when (this) {
        is RecurringExpense -> {
            Expense(
                id = id,
                type = ExpenseType.recurring,
                recurringExpenseStatus = status.toModel(),
                category = category.toModel(),
                recipientId = recipientId,
                concept = concept,
                comments = comments,
                amount = amount,
                amountType = amountType.toModel(),
                paymentMethod = paymentMethod.toModel(),
                frequency = frequency.toModel(),
                firstPaymentDate = firstPaymentDate,
                lastPaymentDate = lastPaymentDate,
                hiddenInPlans = hiddenInPlans,
                paymentsIds = paymentsIds,
                createdAt = createdAt.toOffsetDateTime(),
                updatedAt = updatedAt.toOffsetDateTime(),
            )
        }

        is OneTimeExpense -> {
            Expense(
                id = id,
                type = ExpenseType.oneTime,
                oneTimeExpenseStatus = status.toModel(),
                category = category.toModel(),
                recipientId = recipientId,
                concept = concept,
                comments = comments,
                amount = amount,
                amountType = amountType.toModel(),
                paymentMethod = paymentMethod.toModel(),
                hiddenInPlans = hiddenInPlans,
                paymentId = paymentId,
                createdAt = createdAt.toOffsetDateTime(),
                updatedAt = updatedAt.toOffsetDateTime(),
            )
        }

        else -> throw IllegalStateException("Unknown expense type")
    }
}
