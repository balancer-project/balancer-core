package io.juancrrn.balancercore.infrastructure.database.models

import org.springframework.data.annotation.Id
import java.util.UUID

data class ExpenseRecipient(
    @Id
    val id: UUID,
    val name: String,
) {

    companion object {

        object Field {

            const val ID = "id"
        }
    }
}
