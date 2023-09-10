package io.juancrrn.balancercore.domain.events

import io.juancrrn.balancercore.domain.valueobjects.TransactionId

data class TransactionRemovedEvent(
    val transactionId: TransactionId,
) : DomainEvent()
