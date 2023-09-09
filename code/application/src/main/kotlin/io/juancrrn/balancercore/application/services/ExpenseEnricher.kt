package io.juancrrn.balancercore.application.services

import io.juancrrn.balancercore.application.dtos.EnrichedExpense
import io.juancrrn.balancercore.application.dtos.EnrichedOneTimeExpense
import io.juancrrn.balancercore.application.dtos.EnrichedRecurringExpense
import io.juancrrn.balancercore.domain.entities.Expense
import io.juancrrn.balancercore.domain.entities.OneTimeExpense
import io.juancrrn.balancercore.domain.entities.RecurringExpense
import io.juancrrn.balancercore.domain.repositories.ExpensePaymentRepository
import io.juancrrn.balancercore.domain.repositories.ExpenseRecipientRepository
import org.springframework.stereotype.Component
import java.lang.IllegalStateException

@Component
class ExpenseEnricher(
    private val expenseRecipientRepository: ExpenseRecipientRepository,
    private val expensePaymentRepository: ExpensePaymentRepository,
) {

    suspend fun enrich(expense: Expense): EnrichedExpense {
        return when (expense) {
            is OneTimeExpense -> EnrichedOneTimeExpense.from(
                expense,
                expenseRecipientRepository.find(expense.recipientId)!!,
                expense.paymentId?.let { expensePaymentRepository.find(it)!! },
            )
            is RecurringExpense -> EnrichedRecurringExpense.from(
                expense,
                expenseRecipientRepository.find(expense.recipientId)!!,
                expense.paymentsIds.map { expensePaymentRepository.find(it)!! },
            )
            else -> throw IllegalStateException("Unknown expense type")
        }
    }
}
