package io.juancrrn.balancercore.infrastructure.repository.database.models

import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentStatus
import io.juancrrn.balancercore.domain.valueobjects.TransactionId
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment
import io.juancrrn.balancercore.domain.entities.ExpensePayment as ExpensePaymentEntity

fun ExpensePayment.toEntity(): ExpensePaymentEntity {
    return ExpensePaymentEntity(
        id = id,
        status = ExpensePaymentStatus.valueOf(status),
        amount = amount,
        authorizationDate = authorizationDate,
        postDate = postDate,
        originTransactionsIds = originTransactionsIds.map { TransactionId(it) },
    )
}
