package io.levelsoftware.sandbox.kotlin.leetcode

import io.levelsoftware.sandbox.kotlin.logMessage
import io.levelsoftware.sandbox.kotlin.measureAverageRuntime
import java.util.*

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */

fun main(args: Array<String>) {
    val nums = generateRandomIntArray(1000)
    val target = nums[500] + nums[750]

    logMessage(optimizedTwoSum(nums, target).toSet().toString())
    logMessage(naiveTwoSum(nums, target).toSet().toString())

    val naiveTime = measureAverageRuntime { naiveTwoSum(nums, target) }
    val optimizedTime = measureAverageRuntime { optimizedTwoSum(nums, target) }

    logMessage("Naive: $naiveTime ns")
    logMessage("Optimized: $optimizedTime ns")
    logMessage("Delta: ${naiveTime - optimizedTime} ns")
}

/*
  Solution runs in O(N) time complexity
  Only a single iteration of the input numbers array is required and each lookup in the HashMap only
  requires O(1) time complexity.
 */
fun optimizedTwoSum(nums: IntArray, target: Int): IntArray {
    // Contains number and index of the number
    val map = HashMap<Int, Int>()

    nums.forEachIndexed { index, num ->
        val compliment = target - num
        val complimentIndex = map[compliment]
        if (complimentIndex != null) return intArrayOf(complimentIndex, index)
        map[num] = index
    }
    throw IllegalArgumentException("Could not find target sum in number list")
}

/*
 Solution runs in O(N^2) time complexity
 */
fun naiveTwoSum(nums: IntArray, target: Int): IntArray {
    nums.forEachIndexed { outerIndex, outerNum ->
        nums.forEachIndexed { innerIndex, innerNum ->
            // Not allowed to use the same element twice
            if (innerIndex != outerIndex && outerNum + innerNum == target) {
                return when {
                    outerIndex > innerIndex -> intArrayOf(innerIndex, outerIndex)
                    else -> intArrayOf(outerIndex, innerIndex)
                }
            }
        }
    }
    return IntArray(0)
}

fun generateRandomIntArray(count: Int): IntArray {
    val random = Random()
    val numbers = arrayListOf<Int>()
    (0 until count).forEach { numbers.add(random.nextInt()) }
    return numbers.toIntArray()
}