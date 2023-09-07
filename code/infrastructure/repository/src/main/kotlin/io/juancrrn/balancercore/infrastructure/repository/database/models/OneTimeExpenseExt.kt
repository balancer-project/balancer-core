package io.juancrrn.balancercore.infrastructure.repository.database.models

import io.juancrrn.balancercore.domain.valueobjects.ExpenseAmountType
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory
import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentMethod
import io.juancrrn.balancercore.domain.valueobjects.OneTimeExpenseStatus
import io.juancrrn.balancercore.domain.valueobjects.UserId
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense
import io.juancrrn.balancercore.domain.entities.OneTimeExpense as OneTimeExpenseEntity

fun OneTimeExpenseEntity.toModel(): OneTimeExpense {
    return OneTimeExpense(
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
        hiddenInPlans = hiddenInPlans,
        paymentId = paymentId,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deletedAt = deletedAt,
    )
}

fun OneTimeExpense.toEntity(): OneTimeExpenseEntity {
    return OneTimeExpenseEntity(
        id = id,
        userId = UserId(userId),
        status = OneTimeExpenseStatus.valueOf(status),
        category = ExpenseCategory.valueOf(category),
        recipientId = recipientId,
        concept = concept,
        comments = comments,
        amount = amount,
        amountType = ExpenseAmountType.valueOf(amountType),
        paymentMethod = ExpensePaymentMethod.valueOf(paymentMethod),
        hiddenInPlans = hiddenInPlans,
        paymentId = paymentId,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deletedAt = deletedAt,
    )
}
