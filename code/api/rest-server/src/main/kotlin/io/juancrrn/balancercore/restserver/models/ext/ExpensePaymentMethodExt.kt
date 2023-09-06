package io.juancrrn.balancercore.restserver.models.ext

import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentMethod.BANK_CARD
import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentMethod.CASH
import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentMethod.DIRECT_DEBIT
import io.juancrrn.balancercore.restserver.api.models.ExpensePaymentMethod
import io.juancrrn.balancercore.restserver.api.models.ExpensePaymentMethod.bankCard
import io.juancrrn.balancercore.restserver.api.models.ExpensePaymentMethod.cash
import io.juancrrn.balancercore.restserver.api.models.ExpensePaymentMethod.directDebit
import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentMethod as ExpensePaymentMethodVO

fun ExpensePaymentMethod.toVO(): ExpensePaymentMethodVO {
    return when (this) {
        cash -> CASH
        bankCard -> BANK_CARD
        directDebit -> DIRECT_DEBIT
    }
}
