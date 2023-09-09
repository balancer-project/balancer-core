package io.juancrrn.balancercore.infrastructure.database.adapters

import io.juancrrn.balancercore.infrastructure.database.models.ExpenseRecipient
import io.juancrrn.balancercore.infrastructure.database.models.RecurringExpense.Companion.Field.ID
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ExpenseRecipientDbAdapter(
    private val entityTemplate: R2dbcEntityTemplate,
) {

    suspend fun findById(id: UUID): ExpenseRecipient? {
        return entityTemplate.selectOne(
            Query.query(
                Criteria.where(ID).`is`(id),
            ),
            ExpenseRecipient::class.java,
        ).awaitFirstOrNull()
    }
}
