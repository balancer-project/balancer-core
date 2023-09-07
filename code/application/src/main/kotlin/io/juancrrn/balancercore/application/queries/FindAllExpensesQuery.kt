package io.juancrrn.balancercore.application.queries

import io.juancrrn.balancercore.domain.entities.Expense
import java.util.UUID

data class FindAllExpensesQuery(
    val userId: UUID,
) : Query<List<Expense>>
