package io.juancrrn.balancercore.infrastructure.repository

import io.juancrrn.balancercore.domain.entities.Expense
import io.juancrrn.balancercore.domain.entities.ExpenseId
import io.juancrrn.balancercore.domain.entities.OneTimeExpense
import io.juancrrn.balancercore.domain.entities.RecurringExpense
import io.juancrrn.balancercore.domain.repositories.ExpenseRepository
import io.juancrrn.balancercore.domain.valueobjects.UserId
import io.juancrrn.balancercore.infrastructure.database.adapters.OneTimeExpenseDbAdapter
import io.juancrrn.balancercore.infrastructure.database.adapters.RecurringExpenseDbAdapter
import io.juancrrn.balancercore.infrastructure.repository.database.models.toEntity
import io.juancrrn.balancercore.infrastructure.repository.database.models.toModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
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

    override suspend fun find(id: ExpenseId): Expense? = coroutineScope {
        return@coroutineScope listOf(
            async { oneTimeExpenseDbAdapter.findById(id)?.toEntity() },
            async { recurringExpenseDbAdapter.findById(id)?.toEntity() },
        ).awaitAll().firstOrNull()
    }

    override suspend fun findAll(userId: UserId): List<Expense> = coroutineScope {
        return@coroutineScope listOf(
            async { oneTimeExpenseDbAdapter.findByUserId(userId.id).map { it.toEntity() } },
            async { recurringExpenseDbAdapter.findByUserId(userId.id).map { it.toEntity() } },
        ).awaitAll().flatten()
    }
}
