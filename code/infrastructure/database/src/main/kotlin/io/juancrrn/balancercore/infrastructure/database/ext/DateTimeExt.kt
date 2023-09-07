package io.juancrrn.balancercore.infrastructure.database.ext

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

val ZONE_OFFSET: ZoneOffset = ZoneOffset.UTC

fun LocalDateTime.toInstant(): Instant {
    return this.toInstant(ZONE_OFFSET)
}
