package io.juancrrn.balancercore.infrastructure.database.adapters

import io.juancrrn.balancercore.infrastructure.database.ext.bindNullable
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Companion.Field.AMOUNT
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Companion.Field.AMOUNT_TYPE
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Companion.Field.CATEGORY
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Companion.Field.COMMENTS
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Companion.Field.CONCEPT
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Companion.Field.CREATED_AT
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Companion.Field.DELETED_AT
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Companion.Field.HIDDEN_IN_PLANS
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Companion.Field.ID
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Companion.Field.PAYMENT_ID
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Companion.Field.PAYMENT_METHOD
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Companion.Field.RECIPIENT_ID
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Companion.Field.STATUS
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Companion.Field.UPDATED_AT
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Companion.Field.USER_ID
import io.juancrrn.balancercore.infrastructure.database.models.OneTimeExpense.Companion.TABLE
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.awaitRowsUpdated
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class OneTimeExpenseDbAdapter(
    private val databaseClient: DatabaseClient,
) {

    suspend fun insert(oneTimeExpense: OneTimeExpense) = oneTimeExpense.run {
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
            .bind(HIDDEN_IN_PLANS, hiddenInPlans)
            .bindNullable(PAYMENT_ID, paymentId)
            .bind(CREATED_AT, createdAt)
            .bind(UPDATED_AT, updatedAt)
            .bindNullable(DELETED_AT, deletedAt)
            .fetch()
            .awaitRowsUpdated()
    }

    suspend fun update(oneTimeExpense: OneTimeExpense) {
        TODO()
    }

    suspend fun findById(id: UUID): OneTimeExpense? {
        return databaseClient
            .sql(SELECT_SQL + WHERE_ID_SQL)
            .bind(ID, id)
            .map { row, _ -> OneTimeExpense.map(row) }
            .one()
            .awaitSingle()
    }

    suspend fun findByUserId(userId: UUID): List<OneTimeExpense> {
        return databaseClient
            .sql(SELECT_SQL + WHERE_USER_ID_SQL)
            .bind(USER_ID, userId)
            .map { row, _ -> OneTimeExpense.map(row) }
            .all()
            .collectList()
            .awaitSingle()
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
                $PAYMENT_ID,
                $CREATED_AT,
                $UPDATED_AT,
                $DELETED_AT
            ) values (
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
                :$PAYMENT_ID,
                :$CREATED_AT,
                :$UPDATED_AT,
                :$DELETED_AT
            )
        """

        private const val SELECT_SQL = """
            select
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
                $PAYMENT_ID,
                $CREATED_AT,
                $UPDATED_AT,
                $DELETED_AT
            from $TABLE
        """

        private const val WHERE_ID_SQL = """
            where $ID = :$ID
        """

        private const val WHERE_USER_ID_SQL = """
            where $USER_ID = :$USER_ID
        """
    }
}
