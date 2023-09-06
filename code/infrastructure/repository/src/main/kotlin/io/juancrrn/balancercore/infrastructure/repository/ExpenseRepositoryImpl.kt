package io.juancrrn.balancercore.infrastructure.repository

import io.juancrrn.balancercore.domain.entities.Expense
import io.juancrrn.balancercore.domain.entities.ExpenseId
import io.juancrrn.balancercore.domain.entities.OneTimeExpense
import io.juancrrn.balancercore.domain.entities.RecurringExpense
import io.juancrrn.balancercore.domain.repositories.ExpenseRepository
import io.juancrrn.balancercore.domain.valueobjects.UserId
import io.juancrrn.balancercore.infrastructure.database.adapters.OneTimeExpenseDbAdapter
import io.juancrrn.balancercore.infrastructure.database.adapters.RecurringExpenseDbAdapter
import io.juancrrn.balancercore.infrastructure.repository.database.models.toModel
import org.springframework.stereotype.Repository

@Repository
class ExpenseRepositoryImpl(
    private val oneTimeExpenseDbAdapter: OneTimeExpenseDbAdapter,
    private val recurringExpenseDbAdapter: RecurringExpenseDbAdapter,
) : ExpenseRepository {

    override suspend fun save(expense: Expense) {
        if (find(expense.id) == null) {
            if (expense is OneTimeExpense) {
                oneTimeExpenseDbAdapter.insert(expense.toModel())
            } else if (expense is RecurringExpense) {
                recurringExpenseDbAdapter.insert(expense.toModel())
            }
        } else {
            if (expense is OneTimeExpense) {
                oneTimeExpenseDbAdapter.update(expense.toModel())
            } else if (expense is RecurringExpense) {
                recurringExpenseDbAdapter.update(expense.toModel())
            }
        }
    }

    override suspend fun find(id: ExpenseId): Expense? {
        return null
        TODO("Not yet implemented")
    }

    override suspend fun find(userId: UserId): List<Expense> {
        TODO("Not yet implemented")
    }
}
