package io.juancrrn.balancercore.infrastructure.database.models

import org.springframework.data.annotation.Id
import java.time.Instant
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

    companion object Field {

        const val TABLE = "one_time_expense"

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
        const val CREATED_AT = "created_at"
        const val UPDATED_AT = "updated_at"
        const val DELETED_AT = "deleted_at"
    }
}
