package io.juancrrn.balancercore.restserver.models.ext

import io.juancrrn.balancercore.restserver.api.models.ExpenseRecipient
import io.juancrrn.balancercore.domain.entities.ExpenseRecipient as ExpenseRecipientEntity

fun ExpenseRecipientEntity.toModel(): ExpenseRecipient {
    return ExpenseRecipient(
        id = id,
        name = name,
    )
}
