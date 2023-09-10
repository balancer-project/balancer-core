package io.juancrrn.balancercore.domain.services

import io.juancrrn.balancercore.domain.entities.Expense
import io.juancrrn.balancercore.domain.entities.OneTimeExpense
import io.juancrrn.balancercore.domain.entities.PreprocessedTransaction
import io.juancrrn.balancercore.domain.entities.RecurringExpense
import io.juancrrn.balancercore.domain.repositories.ExpensePaymentRepository
import io.juancrrn.balancercore.domain.repositories.ExpenseRepository
import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentStatus
import io.juancrrn.balancercore.domain.valueobjects.OneTimeExpenseStatus
import org.springframework.stereotype.Service

@Service
class TransactionExpenseProcessor(
    private val expenseRepository: ExpenseRepository,
    private val expensePaymentRepository: ExpensePaymentRepository,
) {

    suspend fun process(transaction: PreprocessedTransaction, expense: Expense) {
        val updatedExpense = when (expense) {
            is OneTimeExpense -> processOneTimeExpense(transaction, expense)
            is RecurringExpense -> processRecurringExpense(transaction, expense)
        }

        expenseRepository.save(updatedExpense)
    }

    internal suspend fun processOneTimeExpense(
        transaction: PreprocessedTransaction,
        expense: OneTimeExpense,
    ): OneTimeExpense {
        var updatedExpense = expense

        if (expense.status == OneTimeExpenseStatus.PENDING) {
            if (expense.paymentId == null) {
                val payment = transaction.toExpensePayment(expenseId = expense.id)
                expensePaymentRepository.save(payment)

                updatedExpense = updatedExpense.copy(
                    status = when (payment.status) {
                        ExpensePaymentStatus.PENDING -> OneTimeExpenseStatus.PENDING
                        ExpensePaymentStatus.POSTED -> OneTimeExpenseStatus.DONE
                    },
                    paymentId = payment.id,
                )
            } else {
                if (!transaction.pending) {
                    val payment = expensePaymentRepository.find(expense.paymentId)!!

                    val updatedPayment = transaction.toExpensePayment(
                        paymentId = payment.id,
                        expenseId = expense.id,
                        originTransactionsIds = payment.originTransactionsIds,
                    )
                    expensePaymentRepository.save(updatedPayment)

                    updatedExpense = updatedExpense.copy(
                        status = when (updatedPayment.status) {
                            ExpensePaymentStatus.PENDING -> OneTimeExpenseStatus.PENDING
                            ExpensePaymentStatus.POSTED -> OneTimeExpenseStatus.DONE
                        },
                        paymentId = updatedPayment.id,
                    )
                }
            }
        }

        // If the expense was already done, ignore the transaction update

        return updatedExpense
    }

    internal suspend fun processRecurringExpense(
        transaction: PreprocessedTransaction,
        expense: RecurringExpense,
    ): RecurringExpense {
        TODO()
    }
}
