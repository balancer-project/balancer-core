package io.juancrrn.balancercore.restserver.models.ext

import io.juancrrn.balancercore.domain.valueobjects.ExpenseAmountType.ESTIMATED
import io.juancrrn.balancercore.domain.valueobjects.ExpenseAmountType.FIXED
import io.juancrrn.balancercore.restserver.api.models.ExpenseAmountType
import io.juancrrn.balancercore.restserver.api.models.ExpenseAmountType.estimated
import io.juancrrn.balancercore.restserver.api.models.ExpenseAmountType.fixed
import io.juancrrn.balancercore.domain.valueobjects.ExpenseAmountType as ExpenseAmountTypeVO

fun ExpenseAmountType.toVO(): ExpenseAmountTypeVO {
    return when (this) {
        fixed -> FIXED
        estimated -> ESTIMATED
    }
}
