package io.juancrrn.balancercore.infrastructure.repository

import io.juancrrn.balancercore.domain.entities.ExpensePayment
import io.juancrrn.balancercore.domain.entities.ExpensePaymentId
import io.juancrrn.balancercore.domain.repositories.ExpensePaymentRepository
import io.juancrrn.balancercore.infrastructure.database.adapters.ExpensePaymentDbAdapter
import io.juancrrn.balancercore.infrastructure.repository.database.models.toEntity
import org.springframework.stereotype.Repository

@Repository
class ExpensePaymentRepositoryImpl(
    private val expensePaymentDbAdapter: ExpensePaymentDbAdapter,
) : ExpensePaymentRepository {

    override suspend fun find(id: ExpensePaymentId): ExpensePayment? {
        return expensePaymentDbAdapter.findById(id)?.toEntity()
    }
}
