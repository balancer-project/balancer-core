package io.juancrrn.balancercore.infrastructure.repository.database.models

import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentStatus
import io.juancrrn.balancercore.domain.valueobjects.TransactionId
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment
import io.juancrrn.balancercore.domain.entities.ExpensePayment as ExpensePaymentEntity

fun ExpensePayment.toEntity(): ExpensePaymentEntity {
    return ExpensePaymentEntity(
        id = id,
        expenseId = expenseId,
        status = ExpensePaymentStatus.valueOf(status),
        amount = amount,
        date = date,
        authorizationDate = authorizationDate,
        originTransactionsIds = originTransactionsIds.map { TransactionId(it) },
    )
}

fun ExpensePaymentEntity.toModel(): ExpensePayment {
    return ExpensePayment(
        id = id,
        expenseId = expenseId,
        status = status.name,
        amount = amount,
        date = date,
        authorizationDate = authorizationDate,
        originTransactionsIds = originTransactionsIds.map { it.id },
    )
}
