package io.juancrrn.balancercore.application.usecases

import io.juancrrn.balancercore.application.commands.CommandUseCase
import io.juancrrn.balancercore.application.commands.ProcessTransactionAddedCommand
import io.juancrrn.balancercore.domain.exceptions.UnableToResolveTransactionExpenseException
import io.juancrrn.balancercore.domain.repositories.ExpensePaymentRepository
import io.juancrrn.balancercore.domain.services.TransactionExpenseProcessor
import io.juancrrn.balancercore.domain.services.TransactionExpenseResolver
import org.springframework.stereotype.Component

@Component
class ProcessTransactionAddedUseCase(
    private val expensePaymentRepository: ExpensePaymentRepository,
    private val transactionExpenseResolver: TransactionExpenseResolver,
    private val transactionExpenseProcessor: TransactionExpenseProcessor,
) : CommandUseCase<ProcessTransactionAddedCommand, Unit> {

    override suspend fun dispatch(command: ProcessTransactionAddedCommand) = command.transactionAdded.run {
        if (expensePaymentRepository.findByOriginTransactionId(transaction.id) != null) {
            return // Ignore transactions that have already been processed
        }

        try {
            val expense = transactionExpenseResolver.resolve(transaction)
            transactionExpenseProcessor.processAddedFor(transaction, expense)
        } catch (_: UnableToResolveTransactionExpenseException) {
        }
    }
}
