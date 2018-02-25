package io.levelsoftware.sandbox.kotlin.leetcode

import io.levelsoftware.sandbox.kotlin.logMessage

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */

fun main(args: Array<String>) {
    val nums = intArrayOf(2, 7, 11, 15)
    val target = 9

    val result = naiveTwoSum(nums, target)
    logMessage(result.toSet().toString())
}

/*
 First solution runs in O(N^2) time complexity
 */
fun naiveTwoSum(nums: IntArray, target: Int): IntArray {
    nums.forEachIndexed { outerIndex, outerNum ->
        nums.forEachIndexed { innerIndex, innerNum ->
            // Not allowed to use the same element twice
            if (innerIndex != outerIndex) {
                if (outerNum + innerNum == target) return intArrayOf(outerNum, innerNum)
            }
        }
    }
    return IntArray(0)
}