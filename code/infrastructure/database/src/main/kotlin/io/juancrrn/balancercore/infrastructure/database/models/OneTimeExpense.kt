package io.juancrrn.balancercore.infrastructure.database.models

import io.juancrrn.balancercore.infrastructure.database.ext.getAs
import io.juancrrn.balancercore.infrastructure.database.ext.toInstant
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
import io.r2dbc.spi.Row
import org.springframework.data.annotation.Id
import java.time.Instant
import java.time.LocalDateTime
import java.util.UUID

data class OneTimeExpense(
    @Id
    val id: UUID,
    val userId: UUID,
    val status: String,
    val category: String,
    val recipientId: UUID,
    val concept: String,
    val comments: String,
    val amount: Float,
    val amountType: String,
    val paymentMethod: String,
    val hiddenInPlans: Boolean,
    val paymentId: UUID?,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?,
) {

    companion object {

        const val TABLE = "one_time_expense"

        fun map(row: Row): OneTimeExpense = row.run {
            return OneTimeExpense(
                id = getAs(ID)!!,
                userId = getAs(USER_ID)!!,
                status = getAs(STATUS)!!,
                category = getAs(CATEGORY)!!,
                recipientId = getAs(RECIPIENT_ID)!!,
                concept = getAs(CONCEPT)!!,
                comments = getAs(COMMENTS)!!,
                amount = getAs(AMOUNT)!!,
                amountType = getAs(AMOUNT_TYPE)!!,
                paymentMethod = getAs(PAYMENT_METHOD)!!,
                hiddenInPlans = getAs(HIDDEN_IN_PLANS)!!,
                paymentId = getAs(PAYMENT_ID),
                createdAt = getAs<LocalDateTime>(CREATED_AT)!!.toInstant(),
                updatedAt = getAs<LocalDateTime>(UPDATED_AT)!!.toInstant(),
                deletedAt = getAs<LocalDateTime>(DELETED_AT)?.toInstant(),
            )
        }

        object Field {

            const val ID = "id"
            const val USER_ID = "user_id"
            const val STATUS = "status"
            const val CATEGORY = "category"
            const val RECIPIENT_ID = "recipient_id"
            const val CONCEPT = "concept"
            const val COMMENTS = "comments"
            const val AMOUNT = "amount"
            const val AMOUNT_TYPE = "amount_type"
            const val PAYMENT_METHOD = "payment_method"
            const val HIDDEN_IN_PLANS = "hidden_in_plans"
            const val PAYMENT_ID = "payment_id"
            const val CREATED_AT = "created_at"
            const val UPDATED_AT = "updated_at"
            const val DELETED_AT = "deleted_at"
        }
    }
}
