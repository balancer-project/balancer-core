package io.juancrrn.balancercore.domain.entities

import java.util.UUID

data class ExpenseCategory(
    val id: ExpenseCategoryId
)

typealias ExpenseCategoryId = UUID
