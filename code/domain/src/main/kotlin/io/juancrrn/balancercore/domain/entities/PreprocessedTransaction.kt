package io.juancrrn.balancercore.domain.entities

import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentStatus
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

data class PreprocessedTransaction(
    val id: TransactionId,
    val userId: UserId,
    val institutionId: InstitutionId,
    val accountId: String,
    val amount: Double,
    val currency: Currency?,
    val name: String,
    val merchantName: String?,
    val pending: Boolean,
    val pendingTransactionId: TransactionId?,
    val authorizedDate: LocalDate?,
    val authorizedDateTime: LocalDateTime?,
    val date: LocalDate,
    val dateTime: LocalDateTime?,
    val paymentChannel: PaymentChannel,
    val primaryPersonalFinanceCategory: TransactionPrimaryCategory?,
    val detailedPersonalFinanceCategory: TransactionDetailedCategory?,
    val type: TransactionType?,
) {

    fun toExpensePayment(
        paymentId: ExpensePaymentId = ExpensePaymentId.randomUUID(),
        expenseId: ExpenseId,
        originTransactionsIds: List<TransactionId> = emptyList(),
    ): ExpensePayment {
        return ExpensePayment(
            id = paymentId,
            expenseId = expenseId,
            status = when (pending) {
                true -> ExpensePaymentStatus.PENDING
                false -> ExpensePaymentStatus.POSTED
            },
            amount = amount.toFloat(),
            date = date,
            authorizationDate = authorizedDate,
            originTransactionsIds = originTransactionsIds + id,
        )
    }
}
