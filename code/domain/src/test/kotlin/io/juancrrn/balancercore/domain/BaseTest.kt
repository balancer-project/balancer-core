package io.juancrrn.balancercore.domain

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

abstract class BaseTest {

    @BeforeEach
    fun _beforeEach() {
        MockKAnnotations.init(this, relaxed = true)
        beforeEach()
    }

    open fun beforeEach() {}

    @AfterEach
    fun _afterEach() {
        afterEach()
        clearAllMocks()
    }

    open fun afterEach() {}
}
