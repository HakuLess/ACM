package leetcode.normal

import utils.print
import utils.toGrid

fun main() {
    val s = Solution1776()
    s.getCollisionTimes("[[1,2],[2,1],[4,3],[7,2]]".toGrid()).print()
    s.getCollisionTimes("[[3,4],[5,4],[6,3],[9,1]]".toGrid()).print()
}

// 单调栈
class Solution1776 {
    fun getCollisionTimes(cars: Array<IntArray>): DoubleArray {

        val n = cars.size
        val ans = DoubleArray(n) { -1.0 }
        val stack = ArrayDeque<Int>()

        for (i in n - 1 downTo 0) {
            val pos = cars[i][0]
            val speed = cars[i][1]

            while (stack.isNotEmpty()) {
                val j = stack.last()
                val posj = cars[j][0]
                val speedj = cars[j][1]

                if (speed <= speedj) {
                    // 追不上
                    stack.removeLast()
                    continue
                }

                // 计算追上时间
                val t = (posj - pos).toDouble() / (speed - speedj)

                // 如果前车会在 t 秒内先和别人相遇并变慢，则当前车要追的是那个新车队
                if (ans[j] > 0 && t > ans[j]) {
                    stack.removeLast()
                } else {
                    ans[i] = t
                    break
                }
            }

            stack.addLast(i)
        }

        return ans
    }
}