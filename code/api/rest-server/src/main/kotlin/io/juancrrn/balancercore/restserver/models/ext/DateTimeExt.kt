package io.juancrrn.balancercore.restserver.models.ext

import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId

private val ZONE = ZoneId.of("Europe/Madrid")

fun Instant.toOffsetDateTime(): OffsetDateTime {
    return OffsetDateTime.ofInstant(this, ZONE)
}
