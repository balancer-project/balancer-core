package io.juancrrn.balancercore.domain.services

import io.juancrrn.balancercore.domain.BaseTest
import io.juancrrn.balancercore.domain.repositories.ExpenseRepository
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TransactionExpenseResolverTests : BaseTest() {

    @MockK
    private lateinit var expenseRepository: ExpenseRepository

    @InjectMockKs
    private lateinit var resolver: TransactionExpenseResolver

    @Test
    fun `words in common percentage test`() {
        with(resolver) {
            assertEquals(1.0f, wordsInCommonPercentage("hola", "hola"))
            assertEquals(0.6666667f, wordsInCommonPercentage("hola", "hola mundo"))
            assertEquals(0.6666667f, wordsInCommonPercentage("hola mundo", "hola"))
            assertEquals(1.0f, wordsInCommonPercentage("hola mundo", "hola mundo"))
            assertEquals(0.8f, wordsInCommonPercentage("hola mundo", "hola mundo mundo"))
            assertEquals(0.8f, wordsInCommonPercentage("hola mundo mundo", "hola mundo"))
            assertEquals(0.6666667f, wordsInCommonPercentage("hola mundo mundo", "hola mundo mundo"))
            assertEquals(0.5714286f, wordsInCommonPercentage("hola mundo mundo", "hola mundo mundo mundo"))
            assertEquals(0.5714286f, wordsInCommonPercentage("hola mundo mundo mundo", "hola mundo mundo"))
            assertEquals(0.5f, wordsInCommonPercentage("hola mundo mundo mundo", "hola mundo mundo mundo"))
            assertEquals(0.44444445f, wordsInCommonPercentage("hola mundo mundo mundo", "hola mundo mundo mundo mundo"))
            assertEquals(0.44444445f, wordsInCommonPercentage("hola mundo mundo mundo mundo", "hola mundo mundo mundo"))
            assertEquals(0.4f, wordsInCommonPercentage("hola mundo mundo mundo mundo", "hola mundo mundo mundo mundo"))
        }
    }
}
