package io.levelsoftware.sandbox.kotlin.leetcode

import io.levelsoftware.sandbox.kotlin.getRandomString
import io.levelsoftware.sandbox.kotlin.logMessage
import io.levelsoftware.sandbox.kotlin.measureAverageRuntime
import java.util.*
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread

/*
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
fun main(args: Array<String>) {
    val longestSubstring = LongestSubstring()
    logMessage(longestSubstring.lengthOfLongestSubStringHashMap("abcabcbb").toString())
    logMessage(longestSubstring.lengthOfLongestSubStringHashMap("bbbbb").toString())
    logMessage(longestSubstring.lengthOfLongestSubStringHashMap("pwwkew").toString())
    logMessage(longestSubstring.lengthOfLongestSubStringHashMap("").toString())
    logMessage(longestSubstring.lengthOfLongestSubStringHashMap("dvdf").toString())
    logMessage(longestSubstring.lengthOfLongestSubStringHashMap("abba").toString())

    val randomString = getRandomString(100000)
    val latch = CountDownLatch(2)

    thread(true) {
        val hashTime = measureAverageRuntime { longestSubstring.lengthOfLongestSubStringHashMap(randomString) }
        logMessage("HashMap time: $hashTime ns")
        latch.countDown()
    }

    thread(true) {
        val arrayTime = measureAverageRuntime { longestSubstring.lengthOfLongestSubStringArray(randomString) }
        logMessage("Array time: $arrayTime ns")
        latch.countDown()
    }
}

class LongestSubstring {
    /*
    Store the last seen index of the characters in an int[] array of fixed size. This is *significantly* faster than looking
    up the entries in a HashMap.
    */
    fun lengthOfLongestSubStringArray(s: String): Int {
        val lastIndex = IntArray(256)
        var startIndex = 0 // Starting index of the substring
        var result = 0

        s.forEachIndexed { index, char ->
            startIndex = Math.max(lastIndex[char.toInt()], startIndex)
            result = Math.max(result, index - startIndex + 1)
            lastIndex[char.toInt()] = index + 1
        }

        return result
    }

    /*
    Store the last seen index of the characters in a HashMap so they can be looked up in O(1) time. This entire
    operation should complete in O(N) time.
    */
    fun lengthOfLongestSubStringHashMap(s: String): Int {
        val lastIndex = HashMap<Char, Int>()
        var startIndex = 0 // Starting index of the substring
        var result = 0

        s.forEachIndexed { index, char ->
            if (lastIndex.containsKey(char)) {
                startIndex = Math.max(lastIndex[char]!!, startIndex)
            }
            result = Math.max(result, index - startIndex + 1)
            lastIndex[char] = index + 1
        }

        return result
    }
}