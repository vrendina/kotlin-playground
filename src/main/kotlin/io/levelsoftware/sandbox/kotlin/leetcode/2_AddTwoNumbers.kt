package io.levelsoftware.sandbox.kotlin.leetcode

import io.levelsoftware.sandbox.kotlin.determineStackSize
import io.levelsoftware.sandbox.kotlin.logMessage

/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order
and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 */
fun main(args: Array<String>) {
    val l1 = ListNode(2, ListNode(4, ListNode(3)))
    val l2 = ListNode(5, ListNode(6, ListNode(4)))

    val adder = AddTwoNumbers()

    logMessage(adder.naiveAddTwoNumbers(l1, l2).toString())
    logMessage(adder.optimizedAddTwoNumbers(l1, l2).toString())

    determineStackSize()
}

class AddTwoNumbers {
    /*
      Optimized implementation without recursion. Time complexity is O(max(l1 size, l2 size)).
     */
    fun optimizedAddTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val output = ListNode()

        // Variables to hold the current node in each linked list
        var cl1 = l1
        var cl2 = l2
        var node = output

        while (true) {
            node.`val` = node.`val` + (cl1?.`val` ?: 0) + (cl2?.`val` ?: 0)
            // If the sum is greater than 10 then carry the 1 to the next node
            if (node.`val` >= 10) {
                node.`val` = node.`val` - 10
                node.next = ListNode(1)
            }

            // If we don't have any additional nodes break out
            if (cl1?.next == null && cl2?.next == null) break

            cl1 = cl1?.next
            cl2 = cl2?.next

            if (node.next == null) node.next = ListNode()
            node = node.next!!
        }

        return output
    }

    /*
     Naive implementation using recursion. The size of the nodes will be limited by the stack size with this
     implementation.
     */
    fun naiveAddTwoNumbers(l1: ListNode?, l2: ListNode?) = naiveAddTwoNumbers(l1, l2, ListNode())

    private fun naiveAddTwoNumbers(l1: ListNode?, l2: ListNode?, result: ListNode): ListNode {
        result.`val` = result.`val` + (l1?.`val` ?: 0) + (l2?.`val` ?: 0)
        // Carry the one if the sum is >= 10 and set the `val` to `val` - 10
        if (result.`val` >= 10) {
            result.`val` = result.`val` - 10
            result.next = ListNode(1)
        }

        // Continue the recursion if either list has a next element
        if (l1?.next != null || l2?.next != null) {
            if (result.next == null) result.next = ListNode()
            naiveAddTwoNumbers(l1?.next, l2?.next, result.next!!)
        }

        return result
    }
}

data class ListNode(var `val`: Int = 0, var next: ListNode? = null)
