package io.juancrrn.balancercore.restserver.models.ext

import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseStatus.ACTIVE
import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseStatus.DONE
import io.juancrrn.balancercore.restserver.api.models.RecurringExpenseStatus
import io.juancrrn.balancercore.restserver.api.models.RecurringExpenseStatus.active
import io.juancrrn.balancercore.restserver.api.models.RecurringExpenseStatus.done
import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseStatus as RecurringExpenseStatusVO

fun RecurringExpenseStatus.toVO(): RecurringExpenseStatusVO {
    return when (this) {
        active -> ACTIVE
        done -> DONE
    }
}

fun RecurringExpenseStatusVO.toModel(): RecurringExpenseStatus {
    return when (this) {
        ACTIVE -> active
        DONE -> done
    }
}
