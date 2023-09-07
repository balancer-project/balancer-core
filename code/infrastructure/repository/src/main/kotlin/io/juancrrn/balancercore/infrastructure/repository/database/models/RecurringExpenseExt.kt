package io.juancrrn.balancercore.infrastructure.repository.database.models

import io.juancrrn.balancercore.domain.valueobjects.ExpenseAmountType
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory
import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentMethod
import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseFrequency
import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseStatus
import io.juancrrn.balancercore.domain.valueobjects.UserId
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

fun RecurringExpense.toEntity(): RecurringExpenseEntity {
    return RecurringExpenseEntity(
        id = id,
        userId = UserId(userId),
        status = RecurringExpenseStatus.valueOf(status),
        category = ExpenseCategory.valueOf(category),
        recipientId = recipientId,
        concept = concept,
        comments = comments,
        amount = amount,
        amountType = ExpenseAmountType.valueOf(amountType),
        paymentMethod = ExpensePaymentMethod.valueOf(paymentMethod),
        frequency = RecurringExpenseFrequency(
            type = RecurringExpenseFrequency.RecurringExpenseFrequencyType.valueOf(frequencyType),
            parameter = frequencyParameter,
        ),
        firstPaymentDate = firstPaymentDate,
        lastPaymentDate = lastPaymentDate,
        hiddenInPlans = hiddenInPlans,
        paymentsIds = paymentsIds,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deletedAt = deletedAt,
    )
}
