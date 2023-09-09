package io.juancrrn.balancercore.infrastructure.repository.database.models

import io.juancrrn.balancercore.infrastructure.database.models.ExpenseRecipient
import io.juancrrn.balancercore.domain.entities.ExpenseRecipient as ExpenseRecipientEntity

fun ExpenseRecipient.toEntity(): ExpenseRecipientEntity {
    return ExpenseRecipientEntity(
        id = id,
        name = name,
    )
}
