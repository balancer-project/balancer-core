package io.juancrrn.balancercore.infrastructure.database.models

import io.juancrrn.balancercore.infrastructure.database.ext.getAs
import io.juancrrn.balancercore.infrastructure.database.ext.toInstant
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
import org.springframework.data.annotation.Id
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class RecurringExpense(
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
    val frequencyType: String,
    val frequencyParameter: Int?,
    val firstPaymentDate: LocalDate,
    val lastPaymentDate: LocalDate?,
    val hiddenInPlans: Boolean,
    val paymentsIds: List<UUID>,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?,
) {

    companion object {

        const val TABLE = "recurring_expense"

        fun mapLeftJoin(joinResult: List<Map<String, Any>>): List<RecurringExpense> {
            return joinResult
                .distinctBy { it.getAs<UUID>(ID)!! }
                .map {
                    RecurringExpense(
                        id = it.getAs(ID)!!,
                        userId = it.getAs(USER_ID)!!,
                        status = it.getAs(STATUS)!!,
                        category = it.getAs(CATEGORY)!!,
                        recipientId = it.getAs(RECIPIENT_ID)!!,
                        concept = it.getAs(CONCEPT)!!,
                        comments = it.getAs(COMMENTS)!!,
                        amount = it.getAs(AMOUNT)!!,
                        amountType = it.getAs(AMOUNT_TYPE)!!,
                        paymentMethod = it.getAs(PAYMENT_METHOD)!!,
                        frequencyType = it.getAs(FREQUENCY_TYPE)!!,
                        frequencyParameter = it.getAs(FREQUENCY_PARAMETER),
                        firstPaymentDate = it.getAs(FIRST_PAYMENT_DATE)!!,
                        lastPaymentDate = it.getAs(LAST_PAYMENT_DATE),
                        hiddenInPlans = it.getAs(HIDDEN_IN_PLANS)!!,
                        paymentsIds = joinResult
                            .filter { payment -> payment.getAs<UUID>(ID) == it.getAs<UUID>(ID) }
                            .mapNotNull { payment -> payment.getAs<UUID>(JOIN_PAYMENT_ID) },
                        createdAt = it.getAs<LocalDateTime>(CREATED_AT)!!.toInstant(),
                        updatedAt = it.getAs<LocalDateTime>(UPDATED_AT)!!.toInstant(),
                        deletedAt = it.getAs<LocalDateTime>(DELETED_AT)?.toInstant(),
                    )
                }
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
            const val FREQUENCY_TYPE = "frequency_type"
            const val FREQUENCY_PARAMETER = "frequency_parameter"
            const val FIRST_PAYMENT_DATE = "first_payment_date"
            const val LAST_PAYMENT_DATE = "last_payment_date"
            const val HIDDEN_IN_PLANS = "hidden_in_plans"
            const val CREATED_AT = "created_at"
            const val UPDATED_AT = "updated_at"
            const val DELETED_AT = "deleted_at"

            const val JOIN_PAYMENT_ID = "payment_id"
        }
    }
}
