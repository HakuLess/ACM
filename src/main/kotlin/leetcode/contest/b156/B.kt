package leetcode.contest.b156

import java.util.*
import kotlin.collections.ArrayList

class SolutionB {
//    fun minOperations(nums: IntArray): Int {
//        fun dfs(l: ArrayList<Int>, min: Int): Int {
//
//        }
//    }

    fun minOperations(nums: IntArray): Int {
        val stack = Stack<Int>()
        stack.push(0)
        var ans = 0
        for (cur in nums) {
            while (stack.peek() > cur) {
                ans++
                stack.pop()
            }
            if (stack.peek() < cur) {
                stack.push(cur)
            }
        }
        while (stack.size > 1) {
            if (stack.peek() > 0) {
                ans++
            }
            stack.pop()
        }
        return ans
    }
}