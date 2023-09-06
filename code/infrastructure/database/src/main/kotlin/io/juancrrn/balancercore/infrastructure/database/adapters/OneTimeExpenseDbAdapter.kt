package io.juancrrn.balancercore.infrastructure.database.adapters

import io.juancrrn.balancercore.infrastructure.database.ext.bindNullable
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Field.AMOUNT
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Field.AMOUNT_TYPE
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Field.CATEGORY
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Field.COMMENTS
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Field.CONCEPT
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Field.CREATED_AT
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Field.DELETED_AT
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Field.HIDDEN_IN_PLANS
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Field.ID
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Field.PAYMENT_METHOD
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Field.RECIPIENT_ID
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Field.STATUS
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Field.TABLE
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Field.UPDATED_AT
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Field.USER_ID
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.awaitRowsUpdated
import org.springframework.stereotype.Component

@Component
class OneTimeExpenseDbAdapter(
    private val databaseClient: DatabaseClient,
) {

    suspend fun insert(oneTimeExpense: OneTimeExpense) {
        databaseClient
            .sql(INSERT_SQL)
            .bind(ID, oneTimeExpense.id)
            .bind(USER_ID, oneTimeExpense.userId)
            .bind(STATUS, oneTimeExpense.status)
            .bind(CATEGORY, oneTimeExpense.category)
            .bind(RECIPIENT_ID, oneTimeExpense.recipientId)
            .bind(CONCEPT, oneTimeExpense.concept)
            .bind(COMMENTS, oneTimeExpense.comments)
            .bind(AMOUNT, oneTimeExpense.amount)
            .bind(AMOUNT_TYPE, oneTimeExpense.amountType)
            .bind(PAYMENT_METHOD, oneTimeExpense.paymentMethod)
            .bind(HIDDEN_IN_PLANS, oneTimeExpense.hiddenInPlans)
            .bind(CREATED_AT, oneTimeExpense.createdAt)
            .bind(UPDATED_AT, oneTimeExpense.updatedAt)
            .bindNullable(DELETED_AT, oneTimeExpense.deletedAt)
            .fetch()
            .awaitRowsUpdated()
    }

    suspend fun update(oneTimeExpense: OneTimeExpense) {
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
                :$HIDDEN_IN_PLANS,
                :$CREATED_AT,
                :$UPDATED_AT,
                :$DELETED_AT
            )
        """
    }
}
