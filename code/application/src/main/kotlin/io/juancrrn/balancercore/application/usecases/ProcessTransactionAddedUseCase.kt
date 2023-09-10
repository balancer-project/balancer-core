package io.juancrrn.balancercore.application.usecases

import io.juancrrn.balancercore.application.commands.CommandUseCase
import io.juancrrn.balancercore.application.commands.ProcessTransactionAddedCommand
import io.juancrrn.balancercore.domain.exceptions.UnableToResolveTransactionExpenseException
import io.juancrrn.balancercore.domain.services.TransactionExpenseProcessor
import io.juancrrn.balancercore.domain.services.TransactionExpenseResolver
import org.springframework.stereotype.Component

@Component
class ProcessTransactionAddedUseCase(
    private val transactionExpenseResolver: TransactionExpenseResolver,
    private val transactionExpenseProcessor: TransactionExpenseProcessor,
) : CommandUseCase<ProcessTransactionAddedCommand, Unit> {

    override suspend fun dispatch(command: ProcessTransactionAddedCommand) = command.transactionAdded.run {
        try {
            val expense = transactionExpenseResolver.resolve(transaction)
            transactionExpenseProcessor.process(transaction, expense)
        } catch (_: UnableToResolveTransactionExpenseException) {
        }
    }
}
