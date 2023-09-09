package io.juancrrn.balancercore.application.usecases

import io.juancrrn.balancercore.application.dtos.EnrichedExpense
import io.juancrrn.balancercore.application.queries.FindAllExpensesQuery
import io.juancrrn.balancercore.application.queries.QueryUseCase
import io.juancrrn.balancercore.application.services.ExpenseEnricher
import io.juancrrn.balancercore.domain.repositories.ExpenseRepository
import io.juancrrn.balancercore.domain.valueobjects.UserId
import org.springframework.stereotype.Component

@Component
class FindAllExpensesUseCase(
    private val expenseRepository: ExpenseRepository,
    private val expenseEnricher: ExpenseEnricher,
) : QueryUseCase<FindAllExpensesQuery, List<EnrichedExpense>> {

    override suspend fun dispatch(query: FindAllExpensesQuery): List<EnrichedExpense> = query.run {
        return expenseRepository.findAll(UserId(userId)).map { expenseEnricher.enrich(it) }
    }
}
