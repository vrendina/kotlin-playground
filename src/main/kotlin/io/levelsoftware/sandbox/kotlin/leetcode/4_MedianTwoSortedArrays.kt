package io.levelsoftware.sandbox.kotlin.leetcode

import io.levelsoftware.sandbox.kotlin.getRandomIntArray
import io.levelsoftware.sandbox.kotlin.logMessage
import io.levelsoftware.sandbox.kotlin.measureAverageRuntime
import kotlin.concurrent.thread

/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */
fun main(args: Array<String>) {
    val nums1 = getRandomIntArray(10000).apply { sort() }
    val nums2 = getRandomIntArray(25000).apply { sort() }

//    val nums1 = intArrayOf(1, 2)
//    val nums2 = intArrayOf(3, 4)

    val median = MedianTwoSortedArrays()
    logMessage(median.findMedianSortedArraysConcise(nums1, nums2).toString())

    thread(true) {
        val naiveTime = measureAverageRuntime { median.findMedianSortedArraysNaive(nums1, nums2) }
        logMessage("Naive time: $naiveTime ns")
    }

    thread(true) {
        val arrayTime = measureAverageRuntime { median.findMedianSortedArraysConcise(nums1, nums2) }
        logMessage("Optimized time: $arrayTime ns")
    }
}

class MedianTwoSortedArrays {
    // This executes in O(N) time complexity, not O(log(m+n)) as required by the problem.
    fun findMedianSortedArraysConcise(nums1: IntArray, nums2: IntArray): Double {
        return (nums1 to nums2).combine().getMedian()
    }

    /**
     * Combine two sorted IntArrays into a single sorted IntArray in O(N) time space.
     */
    private fun Pair<IntArray, IntArray>.combine(): IntArray {
        // Don't attempt to do any combination if one of the arrays is empty
        when {
            first.isEmpty() && second.isNotEmpty() -> return second
            second.isEmpty() && first.isNotEmpty() -> return first
        }

        val combined = IntArray(first.size + second.size)

        var x = 0
        var y = 0

        for (k in 0 until combined.size) {
            val num1 = if (x < first.size) first[x] else null
            val num2 = if (y < second.size) second[y] else null

            when {
                num1 != null && num2 != null -> {
                    when {
                        num1 > num2 -> {
                            combined[k] = num2
                            y++
                        }
                        num1 < num2 || num1 == num2 -> {
                            combined[k] = num1
                            x++
                        }
                    }
                }
                num2 == null && num1 != null -> {
                    combined[k] = num1
                    x++
                }
                num1 == null && num2 != null -> {
                    combined[k] = num2
                    y++
                }
            }
        }

        return combined
    }

    /**
     * Get the median value from an IntArray.
     */
    private fun IntArray.getMedian(): Double {
        return if (size % 2 == 0) {
            (get(size / 2) + get(size / 2 - 1)) / 2.0
        } else {
            get((size - 1) / 2).toDouble()
        }
    }

    fun findMedianSortedArraysNaive(nums1: IntArray, nums2: IntArray): Double {
        val combined = arrayListOf<Int>()
        combined.addAll(nums1.toList())
        combined.addAll(nums2.toList())
        combined.sort()

        return if (combined.size % 2 == 0) {
            (combined[combined.size / 2] + combined[combined.size / 2 - 1]) / 2.0
        } else {
            combined[(combined.size - 1) / 2].toDouble()
        }
    }
}