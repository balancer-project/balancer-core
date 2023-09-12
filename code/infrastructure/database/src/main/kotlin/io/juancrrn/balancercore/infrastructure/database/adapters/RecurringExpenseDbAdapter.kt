package io.juancrrn.balancercore.infrastructure.database.adapters

import io.juancrrn.balancercore.infrastructure.database.ext.bindNullable
import io.juancrrn.balancercore.infrastructure.database.models.ExpensePayment
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.AMOUNT
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.AMOUNT_TYPE
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.CATEGORY
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.COMMENTS
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.CONCEPT
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.CREATED_AT
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.DELETED_AT
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.FIRST_PAYMENT_DATE
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.FREQUENCY_PARAMETER
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.FREQUENCY_TYPE
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.HIDDEN_IN_PLANS
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.ID
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.JOIN_PAYMENT_ID
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.LAST_PAYMENT_DATE
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.PAYMENT_METHOD
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.RECIPIENT_ID
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.STATUS
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.UPDATED_AT
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.USER_ID
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.TABLE
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.awaitRowsUpdated
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class RecurringExpenseDbAdapter(
    private val databaseClient: DatabaseClient,
) {

    suspend fun insert(recurringExpense: RecurringExpense) = recurringExpense.run {
        databaseClient
            .sql(INSERT_SQL)
            .bind(ID, id)
            .bind(USER_ID, userId)
            .bind(STATUS, status)
            .bind(CATEGORY, category)
            .bind(RECIPIENT_ID, recipientId)
            .bind(CONCEPT, concept)
            .bind(COMMENTS, comments)
            .bind(AMOUNT, amount)
            .bind(AMOUNT_TYPE, amountType)
            .bind(PAYMENT_METHOD, paymentMethod)
            .bind(FREQUENCY_TYPE, frequencyType)
            .bindNullable(FREQUENCY_PARAMETER, frequencyParameter)
            .bind(FIRST_PAYMENT_DATE, firstPaymentDate)
            .bindNullable(LAST_PAYMENT_DATE, lastPaymentDate)
            .bind(HIDDEN_IN_PLANS, hiddenInPlans)
            .bind(CREATED_AT, createdAt)
            .bind(UPDATED_AT, updatedAt)
            .bindNullable(DELETED_AT, deletedAt)
            .fetch()
            .awaitRowsUpdated()
    }

    suspend fun update(recurringExpense: RecurringExpense) = recurringExpense.run {
        databaseClient
            .sql(UPDATE_SQL)
            .bind(ID, id)
            .bind(USER_ID, userId)
            .bind(STATUS, status)
            .bind(CATEGORY, category)
            .bind(RECIPIENT_ID, recipientId)
            .bind(CONCEPT, concept)
            .bind(COMMENTS, comments)
            .bind(AMOUNT, amount)
            .bind(AMOUNT_TYPE, amountType)
            .bind(PAYMENT_METHOD, paymentMethod)
            .bind(FREQUENCY_TYPE, frequencyType)
            .bindNullable(FREQUENCY_PARAMETER, frequencyParameter)
            .bind(FIRST_PAYMENT_DATE, firstPaymentDate)
            .bindNullable(LAST_PAYMENT_DATE, lastPaymentDate)
            .bind(HIDDEN_IN_PLANS, hiddenInPlans)
            .bind(CREATED_AT, createdAt)
            .bind(UPDATED_AT, updatedAt)
            .bindNullable(DELETED_AT, deletedAt)
            .fetch()
            .awaitRowsUpdated()
    }

    suspend fun findById(id: UUID): RecurringExpense? {
        return databaseClient
            .sql(SELECT_SQL + WHERE_ID_SQL)
            .bind(ID, id)
            .fetch()
            .all()
            .collectList()
            .awaitSingle()
            .let { RecurringExpense.mapLeftJoin(it) }
            .firstOrNull()
    }

    suspend fun findByUserId(userId: UUID): List<RecurringExpense> {
        return databaseClient
            .sql(SELECT_SQL + WHERE_USER_ID_SQL)
            .bind(USER_ID, userId)
            .fetch()
            .all()
            .collectList()
            .awaitSingle()
            .let { RecurringExpense.mapLeftJoin(it) }
    }

    companion object {

        private const val INSERT_SQL = """
            insert into $TABLE (
                $ID,
                $USER_ID,
                $STATUS,
                $CATEGORY,
                $RECIPIENT_ID,
                $CONCEPT,
                $COMMENTS,
                $AMOUNT,
                $AMOUNT_TYPE,
                $PAYMENT_METHOD,
                $FREQUENCY_TYPE,
                $FREQUENCY_PARAMETER,
                $FIRST_PAYMENT_DATE,
                $LAST_PAYMENT_DATE,
                $HIDDEN_IN_PLANS,
                $CREATED_AT,
                $UPDATED_AT,
                $DELETED_AT
            ) VALUES (
                :$ID,
                :$USER_ID,
                :$STATUS,
                :$CATEGORY,
                :$RECIPIENT_ID,
                :$CONCEPT,
                :$COMMENTS,
                :$AMOUNT,
                :$AMOUNT_TYPE,
                :$PAYMENT_METHOD,
                :$FREQUENCY_TYPE,
                :$FREQUENCY_PARAMETER,
                :$FIRST_PAYMENT_DATE,
                :$LAST_PAYMENT_DATE,
                :$HIDDEN_IN_PLANS,
                :$CREATED_AT,
                :$UPDATED_AT,
                :$DELETED_AT
            )
        """

        private const val UPDATE_SQL = """
            update $TABLE set
                $USER_ID = :$USER_ID,
                $STATUS = :$STATUS,
                $CATEGORY = :$CATEGORY,
                $RECIPIENT_ID = :$RECIPIENT_ID,
                $CONCEPT = :$CONCEPT,
                $COMMENTS = :$COMMENTS,
                $AMOUNT = :$AMOUNT,
                $AMOUNT_TYPE = :$AMOUNT_TYPE,
                $PAYMENT_METHOD = :$PAYMENT_METHOD,
                $FREQUENCY_TYPE = :$FREQUENCY_TYPE,
                $FREQUENCY_PARAMETER = :$FREQUENCY_PARAMETER,
                $FIRST_PAYMENT_DATE = :$FIRST_PAYMENT_DATE,
                $LAST_PAYMENT_DATE = :$LAST_PAYMENT_DATE,
                $HIDDEN_IN_PLANS = :$HIDDEN_IN_PLANS,
                $CREATED_AT = :$CREATED_AT,
                $UPDATED_AT = :$UPDATED_AT,
                $DELETED_AT = :$DELETED_AT
            where $ID = :$ID
        """

        private const val SELECT_SQL = """
            select
                e.$ID,
                e.$USER_ID,
                e.$STATUS,
                e.$CATEGORY,
                e.$RECIPIENT_ID,
                e.$CONCEPT,
                e.$COMMENTS,
                e.$AMOUNT,
                e.$AMOUNT_TYPE,
                e.$PAYMENT_METHOD,
                e.$FREQUENCY_TYPE,
                e.$FREQUENCY_PARAMETER,
                e.$FIRST_PAYMENT_DATE,
                e.$LAST_PAYMENT_DATE,
                e.$HIDDEN_IN_PLANS,
                e.$CREATED_AT,
                e.$UPDATED_AT,
                e.$DELETED_AT,
                p.${ExpensePayment.Companion.Field.ID} as $JOIN_PAYMENT_ID
            from $TABLE as e
            left join ${ExpensePayment.TABLE} as p
                on p.${ExpensePayment.Companion.Field.EXPENSE_ID} = e.$ID
        """

        private const val WHERE_ID_SQL = """
            where e.$ID = :$ID
        """

        private const val WHERE_USER_ID_SQL = """
            where e.$USER_ID = :$USER_ID
        """
    }
}
