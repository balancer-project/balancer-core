package io.juancrrn.balancercore.eventsubscribers.consumers

import io.juancrrn.balancercore.application.commands.ProcessTransactionAddedCommand
import io.juancrrn.balancercore.application.usecases.UseCaseDispatcher
import io.juancrrn.balancercore.eventsubscribers.models.TransactionAdded
import kotlinx.coroutines.runBlocking

class TransactionAddedConsumer(
    private val dispatcher: UseCaseDispatcher,
) : (TransactionAdded) -> Unit {

    override fun invoke(model: TransactionAdded) = runBlocking {
        dispatcher.dispatch(ProcessTransactionAddedCommand(model.toEvent()))
    }
}
