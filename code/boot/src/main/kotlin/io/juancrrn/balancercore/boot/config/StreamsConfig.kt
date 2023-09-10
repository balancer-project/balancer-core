package io.juancrrn.balancercore.boot.config

import io.juancrrn.balancercore.application.usecases.UseCaseDispatcher
import io.juancrrn.balancercore.eventsubscribers.consumers.TransactionAddedConsumer
import io.juancrrn.balancercore.eventsubscribers.models.TransactionAdded
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StreamsConfig(
    private val dispatcher: UseCaseDispatcher,
) {

    @Bean
    fun transactionAdded(): (TransactionAdded) -> Unit {
        return TransactionAddedConsumer(dispatcher)
    }
}
