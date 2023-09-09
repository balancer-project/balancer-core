package io.juancrrn.balancercore.infrastructure.database.models

import io.juancrrn.balancercore.infrastructure.database.ext.getAs
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.Field.AMOUNT
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.Field.AUTHORIZATION_DATE
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.Field.EXPENSE_ID
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.Field.ID
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.Field.JOIN_ORIGIN_TRANSACTION_ID
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.Field.POST_DATE
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.Field.STATUS
import org.springframework.data.annotation.Id
import java.time.LocalDate
import java.util.UUID

data class ExpensePayment(
    @Id
    val id: UUID,
    val expenseId: UUID,
    val status: String,
    val amount: Float,
    val authorizationDate: LocalDate,
    val postDate: LocalDate?,
    val originTransactionsIds: List<String>,
) {

    companion object {

        const val TABLE = "expense_payment"

        const val EXPENSE_PAYMENT_ORIGIN_TRANSACTION_TABLE = "expense_payment_origin_transaction"

        fun mapLeftJoin(joinResult: List<Map<String, Any>>): List<ExpensePayment> {
            return joinResult
                .distinctBy { it.getAs<UUID>(ID)!! }
                .map {
                    ExpensePayment(
                        id = it.getAs(ID)!!,
                        expenseId = it.getAs(EXPENSE_ID)!!,
                        status = it.getAs(STATUS)!!,
                        amount = it.getAs(AMOUNT)!!,
                        authorizationDate = it.getAs(AUTHORIZATION_DATE)!!,
                        postDate = it.getAs(POST_DATE),
                        originTransactionsIds = joinResult
                            .filter { transaction -> transaction.getAs<UUID>(ID) == it.getAs<UUID>(ID) }
                            .mapNotNull { transaction -> transaction.getAs<String>(JOIN_ORIGIN_TRANSACTION_ID) },
                    )
                }
        }

        object Field {

            const val ID = "id"
            const val EXPENSE_ID = "expense_id"
            const val STATUS = "status"
            const val AMOUNT = "amount"
            const val AUTHORIZATION_DATE = "authorization_date"
            const val POST_DATE = "post_date"

            const val JOIN_PAYMENT_ID = "payment_id"
            const val JOIN_TRANSACTION_ID = "transaction_id"
            const val JOIN_ORIGIN_TRANSACTION_ID = "origin_transaction_id"
        }
    }
}
