package leetcode.contest.c280

import utils.print
import kotlin.math.pow

fun main() {
    val s = SolutionD()
    s.maximumANDSum(intArrayOf(1, 2, 3, 4, 5, 6), 3).print()
}

class SolutionD {
    fun maximumANDSum(nums: IntArray, numSlots: Int): Int {
        val n = numSlots
        var ans = 0
        val state = IntArray(n)
        val seen = HashSet<String>()
        fun dfs(index: Int, cur: Int, st: Int) {
            val key = "$index $cur $st"
            if (key in seen) return
            seen.add(key)
            if (index !in nums.indices) {
                ans = maxOf(ans, cur)
                return
            }
            for (i in 0 until n) {
                if (state[i] == 0 || state[i] == 1) {
                    state[i]++
                    dfs(index + 1, cur + (nums[index] and (i + 1)), st + 3.0.pow(i.toDouble()).toInt())
                    state[i]--
                }
            }
        }
        dfs(0, 0, 0)
        return ans
    }
}