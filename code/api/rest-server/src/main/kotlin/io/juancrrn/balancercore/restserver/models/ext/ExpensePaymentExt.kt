package io.juancrrn.balancercore.restserver.models.ext

import io.juancrrn.balancercore.restserver.api.models.ExpensePayment
import io.juancrrn.balancercore.domain.entities.ExpensePayment as ExpensePaymentEntity

fun ExpensePaymentEntity.toModel(): ExpensePayment {
    return ExpensePayment(
        id = id,
        status = status.toModel(),
        amount = amount,
        authorizationDate = authorizationDate,
        postDate = postDate,
    )
}
