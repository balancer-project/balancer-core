package io.juancrrn.balancercore.infrastructure.database.adapters

import io.juancrrn.balancercore.infrastructure.database.ext.bindNullable
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.AMOUNT
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.AMOUNT_TYPE
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.CATEGORY
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.COMMENTS
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.CONCEPT
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.CREATED_AT
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.DELETED_AT
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.FIRST_PAYMENT_DATE
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.FREQUENCY_PARAMETER
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.FREQUENCY_TYPE
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.HIDDEN_IN_PLANS
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.ID
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.LAST_PAYMENT_DATE
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.PAYMENT_METHOD
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.RECIPIENT_ID
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.STATUS
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.TABLE
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.UPDATED_AT
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Field.USER_ID
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.awaitRowsUpdated
import org.springframework.stereotype.Component

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

    suspend fun update(recurringExpense: RecurringExpense) {
        TODO()
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
    }
}
