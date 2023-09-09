package io.juancrrn.balancercore.restserver.models.ext

import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentStatus.PENDING
import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentStatus.POSTED
import io.juancrrn.balancercore.restserver.api.models.ExpensePaymentStatus
import io.juancrrn.balancercore.restserver.api.models.ExpensePaymentStatus.pending
import io.juancrrn.balancercore.restserver.api.models.ExpensePaymentStatus.posted
import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentStatus as ExpensePaymentStatusVO

fun ExpensePaymentStatusVO.toModel(): ExpensePaymentStatus {
    return when (this) {
        PENDING -> pending
        POSTED -> posted
    }
}
