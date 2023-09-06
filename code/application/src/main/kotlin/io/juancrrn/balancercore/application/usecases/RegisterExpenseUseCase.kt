package io.juancrrn.balancercore.application.usecases

import io.juancrrn.balancercore.application.commands.CommandUseCase
import io.juancrrn.balancercore.application.commands.RegisterExpenseCommand
import io.juancrrn.balancercore.domain.entities.ExpenseId
import io.juancrrn.balancercore.domain.repositories.ExpenseRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class RegisterExpenseUseCase(
    private val expenseRepository: ExpenseRepository,
) : CommandUseCase<RegisterExpenseCommand, UUID> {

    override suspend fun dispatch(command: RegisterExpenseCommand): UUID {
        // TODO: validate command

        // TODO: validate that the user exists

        val expense = command.toExpense(ExpenseId.randomUUID())

        expenseRepository.save(expense)

        return expense.id
    }
}
