package io.juancrrn.balancercore.restserver.controllers

import io.juancrrn.balancercore.application.queries.FindAllExpensesQuery
import io.juancrrn.balancercore.application.usecases.UseCaseDispatcher
import io.juancrrn.balancercore.restserver.api.ExpensesApi
import io.juancrrn.balancercore.restserver.api.models.EnrichedExpense
import io.juancrrn.balancercore.restserver.api.models.RegisterExpense200Response
import io.juancrrn.balancercore.restserver.api.models.RegisterExpenseRequest
import io.juancrrn.balancercore.restserver.models.ext.toCommand
import io.juancrrn.balancercore.restserver.models.ext.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
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

    override fun findExpenses(userId: UUID): ResponseEntity<Flow<EnrichedExpense>> {
        return ResponseEntity.ok().body(
            flow {
                emitAll(dispatcher.dispatch(FindAllExpensesQuery(userId)).map { it.toModel() }.asFlow())
            },
        )
    }
}
