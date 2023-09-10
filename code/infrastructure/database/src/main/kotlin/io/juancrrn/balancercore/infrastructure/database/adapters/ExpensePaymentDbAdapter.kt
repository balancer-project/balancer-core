package io.juancrrn.balancercore.infrastructure.database.adapters

import io.juancrrn.balancercore.infrastructure.database.ext.bindNullable
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.EXPENSE_PAYMENT_ORIGIN_TRANSACTION_TABLE
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.Field.AMOUNT
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.Field.AUTHORIZATION_DATE
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.Field.DATE
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.Field.EXPENSE_ID
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.Field.ID
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.Field.JOIN_ORIGIN_TRANSACTION_ID
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.Field.JOIN_PAYMENT_ID
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.Field.JOIN_TRANSACTION_ID
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.Field.STATUS
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment.Companion.TABLE
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePaymentOriginTransaction
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ExpensePaymentDbAdapter(
    private val databaseClient: DatabaseClient,
    private val expensePaymentOriginTransactionDbAdapter: ExpensePaymentOriginTransactionDbAdapter,
) {

    suspend fun insert(expensePayment: ExpensePayment) {
        databaseClient
            .sql(INSERT_SQL)
            .bind(ID, expensePayment.id)
            .bind(EXPENSE_ID, expensePayment.expenseId)
            .bind(STATUS, expensePayment.status)
            .bind(AMOUNT, expensePayment.amount)
            .bind(DATE, expensePayment.date)
            .bindNullable(AUTHORIZATION_DATE, expensePayment.authorizationDate)
            .fetch()
            .rowsUpdated()
            .awaitSingle()

        expensePaymentOriginTransactionDbAdapter.updateMultiple(
            expensePayment.originTransactionsIds.map { ExpensePaymentOriginTransaction(expensePayment.id, it) },
        )
    }

    suspend fun update(expensePayment: ExpensePayment) {
        databaseClient
            .sql(UPDATE_SQL)
            .bind(ID, expensePayment.id)
            .bind(STATUS, expensePayment.status)
            .bind(AMOUNT, expensePayment.amount)
            .bind(DATE, expensePayment.date)
            .bindNullable(AUTHORIZATION_DATE, expensePayment.authorizationDate)
            .fetch()
            .rowsUpdated()
            .awaitSingle()

        expensePaymentOriginTransactionDbAdapter.updateMultiple(
            expensePayment.originTransactionsIds.map { ExpensePaymentOriginTransaction(expensePayment.id, it) },
        )
    }

    suspend fun findById(id: UUID): ExpensePayment? {
        return databaseClient
            .sql(SELECT_SQL + WHERE_ID_SQL)
            .bind(ID, id)
            .fetch()
            .all()
            .collectList()
            .awaitSingle()
            .let { ExpensePayment.mapLeftJoin(it) }
            .firstOrNull()
    }

    companion object {

        private const val INSERT_SQL = """
            insert into $TABLE (
                $ID,
                $EXPENSE_ID,
                $STATUS,
                $AMOUNT,
                $DATE,
                $AUTHORIZATION_DATE
            ) values (
                :$ID,
                :$EXPENSE_ID,
                :$STATUS,
                :$AMOUNT,
                :$DATE,
                :$AUTHORIZATION_DATE
            )
        """

        private const val UPDATE_SQL = """
            update $TABLE set
                $STATUS = :$STATUS,
                $AMOUNT = :$AMOUNT,
                $DATE = :$DATE,
                $AUTHORIZATION_DATE = :$AUTHORIZATION_DATE
            where $ID = :$ID
        """

        private const val SELECT_SQL = """
            select
                p.$ID,
                p.$EXPENSE_ID,
                p.$STATUS,
                p.$AMOUNT,
                p.$DATE,
                p.$AUTHORIZATION_DATE,
                t.$JOIN_TRANSACTION_ID as $JOIN_ORIGIN_TRANSACTION_ID
            from $TABLE as p
            left join $EXPENSE_PAYMENT_ORIGIN_TRANSACTION_TABLE as t
                on $JOIN_PAYMENT_ID = p.$ID
        """

        private const val WHERE_ID_SQL = """
            where p.$ID = :$ID
        """
    }
}
