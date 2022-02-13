package leetcode.contest.c280

import utils.print
import kotlin.math.pow

fun main() {
    val s = SolutionD()
    s.maximumANDSum(intArrayOf(1, 2, 3, 4, 5, 6), 3).print()
}

class SolutionD {
    fun maximumANDSum(nums: IntArray, numSlots: Int): Int {
        var ans = 0
        val dp = IntArray(1 shl numSlots * 2)
        for (i in dp.indices) {
            val c = Integer.bitCount(i)
            if (c >= nums.size) continue
            for (j in 0 until numSlots * 2) {
                if (i and (1 shl j) == 0) {
                    val s = i or (1 shl j)
                    dp[s] = maxOf(dp[s], dp[i] + (j / 2 + 1 and nums[c]))
                    ans = maxOf(ans, dp[s])
                }
            }
        }
        return ans
    }

//    fun maximumANDSum(nums: IntArray, numSlots: Int): Int {
//        val n = numSlots
//        var ans = 0
//        val state = IntArray(n)
//        val seen = HashSet<String>()
//        fun dfs(index: Int, cur: Int, st: Int) {
//            val key = "$index $cur $st"
//            if (key in seen) return
//            seen.add(key)
//            if (index !in nums.indices) {
//                ans = maxOf(ans, cur)
//                return
//            }
//            for (i in 0 until n) {
//                if (state[i] == 0 || state[i] == 1) {
//                    state[i]++
//                    dfs(index + 1, cur + (nums[index] and (i + 1)), st + 3.0.pow(i.toDouble()).toInt())
//                    state[i]--
//                }
//            }
//        }
//        dfs(0, 0, 0)
//        return ans
//    }
}