package io.juancrrn.balancercore.domain.entities

import io.juancrrn.balancercore.domain.valueobjects.UserId

/**
 * A Balancer user.
 *
 * @property id Unique identifier.
 */
data class User(
    val id: UserId,
)
