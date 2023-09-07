package io.juancrrn.balancercore.restserver.models.ext

import io.juancrrn.balancercore.domain.valueobjects.OneTimeExpenseStatus.DONE
import io.juancrrn.balancercore.domain.valueobjects.OneTimeExpenseStatus.PENDING
import io.juancrrn.balancercore.restserver.api.models.OneTimeExpenseStatus
import io.juancrrn.balancercore.restserver.api.models.OneTimeExpenseStatus.done
import io.juancrrn.balancercore.restserver.api.models.OneTimeExpenseStatus.pending
import io.juancrrn.balancercore.domain.valueobjects.OneTimeExpenseStatus as OneTimeExpenseStatusVO

fun OneTimeExpenseStatus.toVO(): OneTimeExpenseStatusVO {
    return when (this) {
        pending -> PENDING
        done -> DONE
    }
}

fun OneTimeExpenseStatusVO.toModel(): OneTimeExpenseStatus {
    return when (this) {
        PENDING -> pending
        DONE -> done
    }
}
