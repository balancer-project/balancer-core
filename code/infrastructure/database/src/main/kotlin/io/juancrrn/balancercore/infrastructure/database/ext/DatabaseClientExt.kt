package io.juancrrn.balancercore.infrastructure.database.ext

import org.springframework.r2dbc.core.DatabaseClient.GenericExecuteSpec

inline fun <reified T> GenericExecuteSpec.bindNullable(name: String, value: T?): GenericExecuteSpec {
    return if (value == null) {
        this.bindNull(name, T::class.java)
    } else {
        this.bind(name, value)
    }
}
