package io.juancrrn.balancercore.restserver.controllers

import io.juancrrn.balancercore.application.usecases.UseCaseDispatcher
import io.juancrrn.balancercore.restserver.api.ExpensesApi
import io.juancrrn.balancercore.restserver.api.models.RegisterExpense200Response
import io.juancrrn.balancercore.restserver.api.models.RegisterExpenseRequest
import io.juancrrn.balancercore.restserver.models.ext.toCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class ExpensesController(
    private val dispatcher: UseCaseDispatcher,
) : ExpensesApi {

    override suspend fun registerExpense(
        userId: UUID,
        registerExpenseRequest: RegisterExpenseRequest,
    ): ResponseEntity<RegisterExpense200Response> {
        val id = dispatcher.dispatch(registerExpenseRequest.toCommand(userId))

        return ResponseEntity.ok().body(RegisterExpense200Response(id))
    }
}
