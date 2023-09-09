package io.juancrrn.balancercore.restserver.models.ext

import io.juancrrn.balancercore.application.dtos.EnrichedOneTimeExpense
import io.juancrrn.balancercore.application.dtos.EnrichedRecurringExpense
import io.juancrrn.balancercore.restserver.api.models.EnrichedExpense
import io.juancrrn.balancercore.restserver.api.models.ExpenseType
import io.juancrrn.balancercore.application.dtos.EnrichedExpense as EnrichedExpenseEntity

fun EnrichedExpenseEntity.toModel(): EnrichedExpense {
    return when (this) {
        is EnrichedRecurringExpense -> {
            EnrichedExpense(
                id = id,
                type = ExpenseType.recurring,
                recurringExpenseStatus = status.toModel(),
                category = category.toModel(),
                recipient = recipient.toModel(),
                concept = concept,
                comments = comments,
                amount = amount,
                amountType = amountType.toModel(),
                paymentMethod = paymentMethod.toModel(),
                frequency = frequency.toModel(),
                firstPaymentDate = firstPaymentDate,
                lastPaymentDate = lastPaymentDate,
                hiddenInPlans = hiddenInPlans,
                payments = payments.map { it.toModel() },
                createdAt = createdAt.toOffsetDateTime(),
                updatedAt = updatedAt.toOffsetDateTime(),
            )
        }

        is EnrichedOneTimeExpense -> {
            EnrichedExpense(
                id = id,
                type = ExpenseType.oneTime,
                oneTimeExpenseStatus = status.toModel(),
                category = category.toModel(),
                recipient = recipient.toModel(),
                concept = concept,
                comments = comments,
                amount = amount,
                amountType = amountType.toModel(),
                paymentMethod = paymentMethod.toModel(),
                hiddenInPlans = hiddenInPlans,
                payment = payment?.toModel(),
                createdAt = createdAt.toOffsetDateTime(),
                updatedAt = updatedAt.toOffsetDateTime(),
            )
        }

        else -> throw IllegalStateException("Unknown expense type")
    }
}
