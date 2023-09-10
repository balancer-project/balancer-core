package io.juancrrn.balancercore.eventsubscribers.models

import io.juancrrn.balancercore.domain.entities.PreprocessedTransaction
import io.juancrrn.balancercore.domain.events.TransactionAddedEvent
import io.juancrrn.balancercore.domain.valueobjects.InstitutionId
import io.juancrrn.balancercore.domain.valueobjects.PaymentChannel
import io.juancrrn.balancercore.domain.valueobjects.TransactionDetailedCategory
import io.juancrrn.balancercore.domain.valueobjects.TransactionId
import io.juancrrn.balancercore.domain.valueobjects.TransactionPrimaryCategory
import io.juancrrn.balancercore.domain.valueobjects.TransactionType
import io.juancrrn.balancercore.domain.valueobjects.UserId
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Currency
import java.util.UUID

data class TransactionAdded(
    val transaction: Transaction,
) {

    fun toEvent(): TransactionAddedEvent {
        return TransactionAddedEvent(
            transaction = transaction.toPreprocessedTransaction(),
        )
    }

    data class Transaction(
        val id: String,
        val userId: String,
        val institutionId: String,
        val accountId: String,
        val amount: Double,
        val currency: String?,
        val name: String,
        val merchantName: String?,
        val pending: Boolean,
        val pendingTransactionId: String?,
        val authorizedDate: String?,
        val authorizedDateTime: String?,
        val date: String,
        val dateTime: String?,
        val paymentChannel: String,
        val primaryPersonalFinanceCategory: String?,
        val detailedPersonalFinanceCategory: String?,
        val type: String?,
    ) {

        fun toPreprocessedTransaction(): PreprocessedTransaction {
            return PreprocessedTransaction(
                id = TransactionId(id),
                userId = UserId(UUID.fromString(userId)),
                institutionId = InstitutionId(institutionId),
                accountId = accountId,
                amount = amount,
                currency = currency?.let { Currency.getInstance(it) },
                name = name,
                merchantName = merchantName,
                pending = pending,
                pendingTransactionId = pendingTransactionId?.let { TransactionId(it) },
                authorizedDate = authorizedDate?.let { LocalDate.parse(it) },
                authorizedDateTime = authorizedDateTime?.let { LocalDateTime.parse(it) },
                date = LocalDate.parse(date),
                dateTime = dateTime?.let { LocalDateTime.parse(it) },
                paymentChannel = PaymentChannel.valueOf(paymentChannel),
                primaryPersonalFinanceCategory = primaryPersonalFinanceCategory?.let {
                    TransactionPrimaryCategory.valueOf(it)
                },
                detailedPersonalFinanceCategory = detailedPersonalFinanceCategory?.let {
                    TransactionDetailedCategory.valueOf(it)
                },
                type = type?.let { TransactionType.valueOf(it) },
            )
        }
    }
}
