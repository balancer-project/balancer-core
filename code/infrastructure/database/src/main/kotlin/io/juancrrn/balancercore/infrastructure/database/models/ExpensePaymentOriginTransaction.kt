package io.juancrrn.balancercore.infrastructure.database.models

import java.util.UUID

data class ExpensePaymentOriginTransaction(
    val paymentId: UUID,
    val transactionId: String,
) {

    companion object {

        const val TABLE = "expense_payment_origin_transaction"

        object Field {

            const val PAYMENT_ID = "payment_id"
            const val TRANSACTION_ID = "transaction_id"
        }
    }
}
