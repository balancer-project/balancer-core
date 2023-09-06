package io.juancrrn.balancercore.application.commands

import io.juancrrn.balancercore.domain.entities.Expense
import io.juancrrn.balancercore.domain.entities.ExpenseId
import io.juancrrn.balancercore.domain.entities.OneTimeExpense
import io.juancrrn.balancercore.domain.entities.RecurringExpense
import io.juancrrn.balancercore.domain.valueobjects.ExpenseAmountType
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory
import io.juancrrn.balancercore.domain.valueobjects.ExpensePaymentMethod
import io.juancrrn.balancercore.domain.valueobjects.OneTimeExpenseStatus
import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseFrequency.RecurringExpenseFrequencyType
import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseStatus
import io.juancrrn.balancercore.domain.valueobjects.UserId
import jakarta.validation.constraints.NotNull
import java.time.Instant
import java.time.LocalDate
import java.util.UUID
import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseFrequency as RecurringExpenseFrequencyVO

data class RegisterExpenseCommand(

    val userId: UUID,
    @field:NotNull
    val type: ExpenseType?,
    val oneTimeExpenseStatus: OneTimeExpenseStatus?,
    val recurringExpenseStatus: RecurringExpenseStatus?,
    @field:NotNull
    val category: ExpenseCategory?,
    @field:NotNull
    val recipientId: UUID?,
    @field:NotNull
    val concept: String?,
    val comments: String?,
    @field:NotNull
    val amount: Float?,
    @field:NotNull
    val amountType: ExpenseAmountType?,
    @field:NotNull
    val paymentMethod: ExpensePaymentMethod?,
    val frequency: RecurringExpenseFrequency?,
    val firstPaymentDate: LocalDate?,
    val lastPaymentDate: LocalDate?,
    @field:NotNull
    val hiddenInPlans: Boolean?,
    val paymentId: UUID?,
    val paymentsIds: List<UUID>?,
) : Command<UUID> {

    fun toExpense(id: ExpenseId): Expense {
        val now = Instant.now()

        return if (type == ExpenseType.ONE_TIME) {
            OneTimeExpense(
                id = id,
                userId = UserId(userId),
                status = oneTimeExpenseStatus!!,
                category = category!!,
                recipientId = recipientId!!,
                concept = concept!!,
                comments = comments ?: "",
                amount = amount!!,
                amountType = amountType!!,
                paymentMethod = paymentMethod!!,
                hiddenInPlans = hiddenInPlans!!,
                paymentId = paymentId,
                createdAt = now,
                updatedAt = now,
            )
        } else {
            RecurringExpense(
                id = id,
                userId = UserId(userId),
                status = recurringExpenseStatus!!,
                category = category!!,
                recipientId = recipientId!!,
                concept = concept!!,
                comments = comments ?: "",
                amount = amount!!,
                amountType = amountType!!,
                paymentMethod = paymentMethod!!,
                frequency = frequency!!.toVO(),
                firstPaymentDate = firstPaymentDate!!,
                lastPaymentDate = lastPaymentDate,
                hiddenInPlans = hiddenInPlans!!,
                paymentsIds = paymentsIds ?: emptyList(),
                createdAt = now,
                updatedAt = now,
            )
        }
    }

    data class RecurringExpenseFrequency(

        @field:NotNull
        val type: RecurringExpenseFrequencyType?,
        @field:NotNull
        val parameter: Int?,
    ) {

        fun toVO(): RecurringExpenseFrequencyVO {
            return RecurringExpenseFrequencyVO(
                type = type!!,
                parameter = parameter!!,
            )
        }
    }

    enum class ExpenseType {

        ONE_TIME,
        RECURRING,
    }
}
