package io.juancrrn.balancercore.restserver.models.ext

import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory.BANK_FEES
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory.ENTERTAINMENT
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory.FOOD_AND_DRINK
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory.GENERAL_MERCHANDISE
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory.GENERAL_SERVICES
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory.GOVERNMENT_AND_NON_PROFIT
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory.HOME_IMPROVEMENT
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory.LOAN_PAYMENTS
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory.MEDICAL
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory.OTHER
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory.PERSONAL_CARE
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory.RENT_AND_UTILITIES
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory.TRANSFER_OUT
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory.TRANSPORTATION
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory.TRAVEL
import io.juancrrn.balancercore.restserver.api.models.ExpenseCategory
import io.juancrrn.balancercore.restserver.api.models.ExpenseCategory.bankFees
import io.juancrrn.balancercore.restserver.api.models.ExpenseCategory.entertainment
import io.juancrrn.balancercore.restserver.api.models.ExpenseCategory.foodAndDrink
import io.juancrrn.balancercore.restserver.api.models.ExpenseCategory.generalMerchandise
import io.juancrrn.balancercore.restserver.api.models.ExpenseCategory.generalServices
import io.juancrrn.balancercore.restserver.api.models.ExpenseCategory.governmentAndNonProfit
import io.juancrrn.balancercore.restserver.api.models.ExpenseCategory.homeImprovement
import io.juancrrn.balancercore.restserver.api.models.ExpenseCategory.loanPayments
import io.juancrrn.balancercore.restserver.api.models.ExpenseCategory.medical
import io.juancrrn.balancercore.restserver.api.models.ExpenseCategory.other
import io.juancrrn.balancercore.restserver.api.models.ExpenseCategory.personalCare
import io.juancrrn.balancercore.restserver.api.models.ExpenseCategory.rentAndUtilities
import io.juancrrn.balancercore.restserver.api.models.ExpenseCategory.transferOut
import io.juancrrn.balancercore.restserver.api.models.ExpenseCategory.transportation
import io.juancrrn.balancercore.restserver.api.models.ExpenseCategory.travel
import io.juancrrn.balancercore.domain.valueobjects.ExpenseCategory as ExpenseCategoryVO

fun ExpenseCategory.toVO(): ExpenseCategoryVO {
    return when (this) {
        transferOut -> TRANSFER_OUT
        loanPayments -> LOAN_PAYMENTS
        bankFees -> BANK_FEES
        entertainment -> ENTERTAINMENT
        foodAndDrink -> FOOD_AND_DRINK
        generalMerchandise -> GENERAL_MERCHANDISE
        homeImprovement -> HOME_IMPROVEMENT
        medical -> MEDICAL
        personalCare -> PERSONAL_CARE
        generalServices -> GENERAL_SERVICES
        governmentAndNonProfit -> GOVERNMENT_AND_NON_PROFIT
        transportation -> TRANSPORTATION
        travel -> TRAVEL
        rentAndUtilities -> RENT_AND_UTILITIES
        other -> OTHER
    }
}
