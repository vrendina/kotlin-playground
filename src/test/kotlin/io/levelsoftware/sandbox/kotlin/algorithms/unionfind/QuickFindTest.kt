package io.levelsoftware.sandbox.kotlin.algorithms.unionfind

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class QuickFindTest {

    private lateinit var quickFind: QuickFind

    @Before
    fun setUp() {
        quickFind = QuickFind(100)
    }

    @Test
    fun arrayIsProperlyInitialized() {
        quickFind.id.forEachIndexed { index, _ ->
            assertTrue(quickFind.id[index] == index)
        }
    }

    @Test
    fun elementsAreConnected() {
        quickFind.union(0, 10)
        quickFind.union(5, 7)
        quickFind.union(5, 8)
        quickFind.union(9, 8)

        assertTrue(quickFind.connected(9, 5))
        assertTrue(quickFind.connected(0, 10))
        assertFalse(quickFind.connected(2, 7))
    }
}