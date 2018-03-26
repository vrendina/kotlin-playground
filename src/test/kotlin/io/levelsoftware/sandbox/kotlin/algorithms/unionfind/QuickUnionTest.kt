package io.levelsoftware.sandbox.kotlin.algorithms.unionfind

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class QuickUnionTest {

    private lateinit var quickUnion: QuickUnion

    @Before
    fun setUp() {
        quickUnion = QuickUnion(100)
    }

    @Test
    fun arrayIsProperlyInitialized() {
        quickUnion.id.forEachIndexed { index, _ ->
            Assert.assertTrue(quickUnion.id[index] == index)
        }
    }

    @Test
    fun rootIsCorrect() {
        // Roots with no unions should be equal to the values
        assertEquals(1, quickUnion.root(1))
        assertEquals(10, quickUnion.root(10))

        // Root of two should be one
        quickUnion.union(1, 2)
        assertEquals(1, quickUnion.root(2))

        // Root of 3 should now be one
        quickUnion.union(2, 3)
        assertEquals(1, quickUnion.root(3))
    }

    @Test
    fun elementsAreConnected() {
        quickUnion.union(0, 10)
        quickUnion.union(5, 7)
        quickUnion.union(5, 8)
        quickUnion.union(9, 8)

        Assert.assertTrue(quickUnion.connected(9, 5))
        Assert.assertTrue(quickUnion.connected(0, 10))
        Assert.assertFalse(quickUnion.connected(2, 7))
    }

}