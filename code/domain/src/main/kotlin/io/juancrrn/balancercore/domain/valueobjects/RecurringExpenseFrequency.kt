package io.juancrrn.balancercore.domain.valueobjects

/**
 * The frequency of a recurring expense.
 *
 * @param type The type of frequency.
 * @param parameter The parameter of the frequency, which will be interpreted differently based on the type.
 */
data class RecurringExpenseFrequency(
    val type: RecurringExpenseFrequencyType,
    val parameter: Int?,
) {

    /**
     * The type of frequency of a recurring expense.
     */
    enum class RecurringExpenseFrequencyType {

        MONTHLY_EXACT_DAY_OF_MONTH,
        MONTHLY_LAST_DAY_OF_MONTH,
        MONTHLY_UNKNOWN,
        WEEKLY_EXACT_DAY_OF_WEEK,
        UNKNOWN,
    }
}
