package io.juancrrn.balancercore.application.usecases

import io.juancrrn.balancercore.application.queries.FindAllExpensesQuery
import io.juancrrn.balancercore.application.queries.QueryUseCase
import io.juancrrn.balancercore.domain.entities.Expense
import io.juancrrn.balancercore.domain.repositories.ExpenseRepository
import io.juancrrn.balancercore.domain.valueobjects.UserId
import org.springframework.stereotype.Component

@Component
class FindAllExpensesUseCase(
    private val expenseRepository: ExpenseRepository,
) : QueryUseCase<FindAllExpensesQuery, List<Expense>> {

    override suspend fun dispatch(query: FindAllExpensesQuery): List<Expense> = query.run {
        return expenseRepository.findAll(UserId(userId))
    }
}
