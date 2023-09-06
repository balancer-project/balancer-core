package io.juancrrn.balancercore.restserver.models.ext

import io.juancrrn.balancercore.application.commands.RegisterExpenseCommand
import io.juancrrn.balancercore.application.commands.RegisterExpenseCommand.ExpenseType.ONE_TIME
import io.juancrrn.balancercore.application.commands.RegisterExpenseCommand.ExpenseType.RECURRING
import io.juancrrn.balancercore.restserver.api.models.ExpenseType
import io.juancrrn.balancercore.restserver.api.models.ExpenseType.oneTime
import io.juancrrn.balancercore.restserver.api.models.ExpenseType.recurring

fun ExpenseType.toRegisterExpenseCommandVO(): RegisterExpenseCommand.ExpenseType {
    return when (this) {
        oneTime -> ONE_TIME
        recurring -> RECURRING
    }
}
