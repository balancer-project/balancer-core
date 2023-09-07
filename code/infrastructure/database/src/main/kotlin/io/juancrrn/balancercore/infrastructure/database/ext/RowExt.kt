package io.juancrrn.balancercore.infrastructure.database.ext

import io.r2dbc.spi.Row

@Suppress("UNCHECKED_CAST")
fun <T> Row.getAs(key: String): T? {
    return get(key) as T?
}

@Suppress("UNCHECKED_CAST")
fun <T> Map<String, Any>.getAs(key: String): T? {
    return get(key) as T?
}
