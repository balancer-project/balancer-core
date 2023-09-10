package io.juancrrn.balancercore.domain.events

import io.juancrrn.balancercore.domain.entities.PreprocessedTransaction

data class TransactionModifiedEvent(
    val transaction: PreprocessedTransaction,
) : DomainEvent()
