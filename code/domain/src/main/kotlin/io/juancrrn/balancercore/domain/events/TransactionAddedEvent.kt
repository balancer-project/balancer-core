package io.juancrrn.balancercore.domain.events

import io.juancrrn.balancercore.domain.entities.PreprocessedTransaction

data class TransactionAddedEvent(
    val transaction: PreprocessedTransaction,
) : DomainEvent()
