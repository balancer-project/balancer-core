package io.juancrrn.balancercore.infrastructure.repository.database.models

import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense
import io.juancrrn.balancercore.domain.entities.RecurringExpense as RecurringExpenseEntity

fun RecurringExpenseEntity.toModel(): RecurringExpense {
    return RecurringExpense(
        id = id,
        userId = userId.id,
        status = status.name,
        category = category.name,
        recipientId = recipientId,
        concept = concept,
        comments = comments,
        amount = amount,
        amountType = amountType.name,
        paymentMethod = paymentMethod.name,
        frequencyType = frequency.type.name,
        frequencyParameter = frequency.parameter,
        firstPaymentDate = firstPaymentDate,
        lastPaymentDate = lastPaymentDate,
        hiddenInPlans = hiddenInPlans,
        paymentsIds = paymentsIds,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deletedAt = deletedAt,
    )
}
