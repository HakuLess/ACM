package leetcode.normal

import utils.print
import java.util.*

fun main() {
    val s = Solution42()
    s.trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)).print()
}

class Solution42 {

    // 单调栈方法
    fun trap(height: IntArray): Int {
        var ans = 0
        val stack = Stack<Int>()
        for (i in height.indices) {
            while (stack.isNotEmpty() && height[i] > height[stack.peek()]) {
                // 遇到更高的柱子
                val top = stack.pop()
                if (stack.isEmpty()) {
                    break
                }
                val left = stack.peek()
                val width = i - left - 1
                val rainHeight = minOf(height[left], height[i]) - height[top]
                ans += width * rainHeight
            }
            stack.push(i)
        }
        return ans
    }

//    fun trap(height: IntArray): Int {
//        val n = height.size
//        val left = IntArray(n)
//        val right = IntArray(n)
//
//        var cur = 0
//        for (i in height.indices) {
//            cur = maxOf(cur, height[i])
//            left[i] = cur
//        }
//
//        cur = 0
//        for (i in height.indices.reversed()) {
//            cur = maxOf(cur, height[i])
//            right[i] = cur
//        }
//
//        left.print()
//        right.print()
//
//        var ans = 0
//        for (i in height.indices) {
//            val total = minOf(left[i], right[i])
//            ans += total - height[i]
//            println("ans add $i: $total - ${height[i]} == ${total - height[i]}")
//        }
//        return ans
//    }
}