package io.juancrrn.balancercore.restserver.models.ext

import io.juancrrn.balancercore.application.commands.RegisterExpenseCommand
import io.juancrrn.balancercore.restserver.api.models.RegisterExpenseRequest
import java.util.UUID

fun RegisterExpenseRequest.toCommand(userId: UUID): RegisterExpenseCommand {
    return RegisterExpenseCommand(
        userId,
        type?.toRegisterExpenseCommandVO(),
        oneTimeExpenseStatus?.toVO(),
        recurringExpenseStatus?.toVO(),
        category?.toVO(),
        recipientId,
        concept,
        comments,
        amount,
        amountType?.toVO(),
        paymentMethod?.toVO(),
        frequency?.toRegisterExpenseCommandVO(),
        firstPaymentDate,
        lastPaymentDate,
        hiddenInPlans,
        paymentId,
        paymentsIds,
    )
}
