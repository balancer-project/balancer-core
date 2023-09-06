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
)
