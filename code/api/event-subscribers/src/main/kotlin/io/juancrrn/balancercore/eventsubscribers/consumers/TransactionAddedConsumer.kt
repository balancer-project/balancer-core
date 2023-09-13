package io.juancrrn.balancercore.eventsubscribers.consumers

import io.juancrrn.balancercore.application.commands.ProcessTransactionAddedCommand
import io.juancrrn.balancercore.application.usecases.UseCaseDispatcher
import io.juancrrn.balancercore.eventsubscribers.models.TransactionAdded
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger

class TransactionAddedConsumer(
    private val logger: Logger,
    private val dispatcher: UseCaseDispatcher,
) : (TransactionAdded) -> Unit {

    override fun invoke(model: TransactionAdded) = runBlocking {
        logger.info("Received transaction added event")
        dispatcher.dispatch(ProcessTransactionAddedCommand(model.toEvent()))
    }
}
