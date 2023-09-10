package io.juancrrn.balancercore.application.commands

import io.juancrrn.balancercore.domain.events.TransactionAddedEvent

data class ProcessTransactionAddedCommand(
    val transactionAdded: TransactionAddedEvent,
) : Command<Unit>
