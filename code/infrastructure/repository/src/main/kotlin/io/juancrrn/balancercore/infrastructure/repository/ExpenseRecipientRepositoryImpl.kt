package io.juancrrn.balancercore.infrastructure.repository

import io.juancrrn.balancercore.domain.entities.ExpenseRecipient
import io.juancrrn.balancercore.domain.entities.ExpenseRecipientId
import io.juancrrn.balancercore.domain.repositories.ExpenseRecipientRepository
import io.juancrrn.balancercore.infrastructure.database.adapters.ExpenseRecipientDbAdapter
import io.juancrrn.balancercore.infrastructure.repository.database.models.toEntity
import org.springframework.stereotype.Repository

@Repository
class ExpenseRecipientRepositoryImpl(
    private val expenseRecipientDbAdapter: ExpenseRecipientDbAdapter,
) : ExpenseRecipientRepository {

    override suspend fun find(id: ExpenseRecipientId): ExpenseRecipient? {
        return expenseRecipientDbAdapter.findById(id)?.toEntity()
    }
}
