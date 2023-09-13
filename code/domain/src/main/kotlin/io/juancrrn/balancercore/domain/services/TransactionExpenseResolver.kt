package io.juancrrn.balancercore.domain.services

import io.juancrrn.balancercore.domain.entities.Expense
import io.juancrrn.balancercore.domain.entities.PreprocessedTransaction
import io.juancrrn.balancercore.domain.exceptions.UnableToResolveTransactionExpenseException
import io.juancrrn.balancercore.domain.repositories.ExpenseRepository
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Service
import kotlin.math.absoluteValue

@Service
class TransactionExpenseResolver(
    private val expenseRepository: ExpenseRepository,
) {

    suspend fun resolve(transaction: PreprocessedTransaction): Expense {
        val userExpenses = expenseRepository.findAll(transaction.userId)
        val classification = userExpenses.map { Pair(it, rateFor(transaction, it)) }
        val bestScore = classification.maxByOrNull { it.second }

        if (bestScore == null || bestScore.second < MIN_SCORE) {
            throw UnableToResolveTransactionExpenseException()
        } else {
            return bestScore.first
        }
    }

    internal fun rateFor(transaction: PreprocessedTransaction, expense: Expense): Float {
        var rate = 0.0f

        if (transaction.primaryPersonalFinanceCategory?.name == expense.category.name) {
            rate += 0.75f
        }

        val amountDifference = (transaction.amount - expense.amount).absoluteValue
        if (amountDifference < 10) {
            rate += 0.4f
        } else if (amountDifference > 50) {
            rate -= 0.4f
        }

        val wordsInCommonPercentage = wordsInCommonPercentage(transaction.name, expense.concept)
        if (wordsInCommonPercentage > 0.60) {
            rate += 0.4f
        } else if (wordsInCommonPercentage < 0.20) {
            rate -= 0.4f
        }

        return rate
    }

    internal fun wordsInCommonPercentage(a: String, b: String): Float {
        val wordsA = StringUtils.stripAccents(a).lowercase().split(" ")
        val wordsB = StringUtils.stripAccents(b).lowercase().split(" ")

        return wordsA.toSet().intersect(wordsB.toSet()).count().toFloat() / ((wordsA.size + wordsB.size).toFloat() / 2)
    }

    companion object {

        private const val MIN_SCORE = 0.2f
    }
}
