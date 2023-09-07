package io.juancrrn.balancercore.infrastructure.database.models

import org.springframework.data.annotation.Id
import java.time.LocalDate
import java.util.UUID

data class ExpensePayment(
    @Id
    val id: UUID,
    val expenseId: UUID,
    val finalAmount: Float,
    val postDate: LocalDate,
) {

    companion object Field {

        const val TABLE = "expense_payment"

        const val ID = "id"
        const val EXPENSE_ID = "expense_id"
        const val FINAL_AMOUNT = "final_amount"
        const val POST_DATE = "post_date"
    }
}
