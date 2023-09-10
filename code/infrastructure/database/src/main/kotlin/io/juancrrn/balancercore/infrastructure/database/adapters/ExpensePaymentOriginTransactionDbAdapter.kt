package io.juancrrn.balancercore.infrastructure.database.adapters

import io.juancrrn.balancercore.infrastructure.database.models.ExpensePaymentOriginTransaction
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePaymentOriginTransaction.Companion.Field.PAYMENT_ID
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePaymentOriginTransaction.Companion.Field.TRANSACTION_ID
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ExpensePaymentOriginTransactionDbAdapter(
    private val entityTemplate: R2dbcEntityTemplate,
) {

    suspend fun insert(expensePaymentOriginTransaction: ExpensePaymentOriginTransaction) {
        entityTemplate.insert(expensePaymentOriginTransaction)
    }

    suspend fun updateMultiple(expensePaymentOriginTransactions: List<ExpensePaymentOriginTransaction>) {
        expensePaymentOriginTransactions
            .filter { findByPaymentIdAndTransactionId(it.paymentId, it.transactionId) == null }
            .forEach { insert(it) }
    }

    suspend fun findByPaymentIdAndTransactionId(
        paymentId: UUID,
        transactionId: String,
    ): ExpensePaymentOriginTransaction? {
        return entityTemplate.select(ExpensePaymentOriginTransaction::class.java)
            .matching(
                Query.query(
                    Criteria.where(PAYMENT_ID).`is`(paymentId)
                        .and(TRANSACTION_ID).`is`(transactionId),
                ),
            )
            .one()
            .awaitSingleOrNull()
    }
}
