package io.juancrrn.balancercore.application.queries

import io.juancrrn.balancercore.application.dtos.EnrichedExpense
import java.util.UUID

data class FindAllExpensesQuery(
    val userId: UUID,
) : Query<List<EnrichedExpense>>
