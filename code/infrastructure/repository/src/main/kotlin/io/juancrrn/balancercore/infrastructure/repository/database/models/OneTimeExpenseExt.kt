package io.juancrrn.balancercore.infrastructure.repository.database.models

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
