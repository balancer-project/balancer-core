package io.juancrrn.balancercore.domain.entities

import java.util.*

data class ExpenseSubcategory(
    val id: ExpenseSubcategoryId,
    val categoryId: ExpenseCategoryId
)

typealias ExpenseSubcategoryId = UUID
