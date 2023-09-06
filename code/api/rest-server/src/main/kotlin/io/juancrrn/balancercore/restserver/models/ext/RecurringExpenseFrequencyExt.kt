package io.juancrrn.balancercore.restserver.models.ext

import io.juancrrn.balancercore.application.commands.RegisterExpenseCommand
import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseFrequency.RecurringExpenseFrequencyType.MONTHLY_EXACT_DAY_OF_MONTH
import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseFrequency.RecurringExpenseFrequencyType.MONTHLY_LAST_DAY_OF_MONTH
import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseFrequency.RecurringExpenseFrequencyType.UNKNOWN
import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseFrequency.RecurringExpenseFrequencyType.WEEKLY_EXACT_DAY_OF_WEEK
import io.juancrrn.balancercore.restserver.api.models.RecurringExpenseFrequency
import io.juancrrn.balancercore.restserver.api.models.RecurringExpenseFrequencyType
import io.juancrrn.balancercore.restserver.api.models.RecurringExpenseFrequencyType.monthlyExactDayOfMonth
import io.juancrrn.balancercore.restserver.api.models.RecurringExpenseFrequencyType.monthlyLastDayOfMonth
import io.juancrrn.balancercore.restserver.api.models.RecurringExpenseFrequencyType.unknown
import io.juancrrn.balancercore.restserver.api.models.RecurringExpenseFrequencyType.weeklyExactDayOfWeek
import io.juancrrn.balancercore.domain.valueobjects.RecurringExpenseFrequency.RecurringExpenseFrequencyType as RecurringExpenseFrequencyTypeVO

fun RecurringExpenseFrequencyType.toVO(): RecurringExpenseFrequencyTypeVO {
    return when (this) {
        monthlyExactDayOfMonth -> MONTHLY_EXACT_DAY_OF_MONTH
        monthlyLastDayOfMonth -> MONTHLY_LAST_DAY_OF_MONTH
        weeklyExactDayOfWeek -> WEEKLY_EXACT_DAY_OF_WEEK
        unknown -> UNKNOWN
    }
}

fun RecurringExpenseFrequency.toRegisterExpenseCommandVO(): RegisterExpenseCommand.RecurringExpenseFrequency {
    return RegisterExpenseCommand.RecurringExpenseFrequency(
        type?.toVO(),
        parameter,
    )
}
