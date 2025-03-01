package leetcode.contest.b151

import utils.print
import java.util.*

fun main() {
    val s = SolutionC()
    s.minCost(intArrayOf(9, 1, 5)).print()
    // 35
    s.minCost(intArrayOf(1, 20, 7, 8, 18)).print()
}

class SolutionC {
    fun minCost(nums: IntArray): Int {

        val n = nums.size
        val seen = HashMap<String, Int>()

        fun dfs(i: Int, m: Int, a: Int): Int {
            val key = "$i,$m,$a"
            if (key in seen.keys) return seen[key]!!
            val remaining = n - i
            val totalRemain = m + remaining
            if (totalRemain <= 2) {
                val elements = mutableListOf<Int>()
                elements.add(a)
                for (j in i until n) elements.add(nums[j])
                return elements.maxOrNull()!!.also { seen[key] = it }
            }
            val x: Int
            val y: Int
            val z: Int
            when (m) {
                0 -> {
                    x = nums[i]
                    y = nums[i + 1]
                    z = nums[i + 2]
                }
                else -> {
                    x = a
                    y = nums[i]
                    z = nums[i + 1]
                }
            }
            val nextPos = i + (3 - m)
            val option1 = maxOf(y, z) + dfs(nextPos, 1, x)
            val option2 = maxOf(x, z) + dfs(nextPos, 1, y)
            val option3 = maxOf(x, y) + dfs(nextPos, 1, z)
            return minOf(option1, option2, option3).also { seen[key] = it }
        }

        return dfs(0, 0, 0)

    }
}