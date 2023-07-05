package io.juancrrn.balancercore.domain.entities

import java.util.*

data class ExpenseTag(
    val id: ExpenseTagId
)

typealias ExpenseTagId = UUID
