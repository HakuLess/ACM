package leetcode.contest.c256

import utils.print
import utils.swap
import kotlin.math.exp

fun main() {
    val s = Solution5856()
    s.minSessions(intArrayOf(2, 3, 3, 4, 4, 4, 6, 7, 8, 9, 10), 15).print()
}

// 状态压缩
//class Solution5856 {
//    fun minSessions(tasks: IntArray, sessionTime: Int): Int {
//        val n = tasks.size
//
//        // 与计算出所有合法组合状态
//        val valid = BooleanArray(1 shl n)
//        for (mask in 1 until valid.size) {
//            var needTime = 0
//            // 该状态下需要的总sessionTime
//            for (i in 0 until n) {
//                if (mask and (1 shl i) != 0) {
//                    needTime += tasks[i]
//                }
//            }
//            if (needTime <= sessionTime) {
//                valid[mask] = true
//            }
//        }
//
//        val dp = IntArray(1 shl n) { Int.MAX_VALUE / 2 }
//        dp[0] = 0
//        for (mask in 1 until dp.size) {
//            // 所有1的组合
//            var subSet = mask
//            while (subSet != 0) {
//                if (valid[subSet]) {
//                    // subSet作为单独一次处理，获取其余部分的最小值
//                    // 处理0010010011之类的状态时，之前比这个更小的子subSet一定处理过了，保证符合预期
//                    dp[mask] = minOf(dp[mask], dp[mask xor subSet] + 1)
//                }
//                subSet = (subSet - 1) and mask
//            }
//        }
//        return dp.last()
//    }
//}

// 模拟退火算法 玄学 最小值
// Simulated Annealing
// 本题tasks的长度<=14
class Solution5856 {
    fun minSessions(tasks: IntArray, sessionTime: Int): Int {
        val eps = 1e-5
        val delta = 0.98
        var ans = Int.MAX_VALUE
        val n = tasks.size

        // 单次结果，并更新全局ans
        fun getAns(): Int {
            var cur = 0
            var res = 1
            for (i in tasks.indices) {
                cur += tasks[i]
                if (cur > sessionTime) {
                    cur = tasks[i]
                    res++
                }
            }
            ans = minOf(ans, res)
            return res
        }

        fun sa() {
            var t = 1e6
            while (t > eps) {
                val x: Int = (0 until n).random()
                val y: Int = (0 until n).random()
                val last: Int = getAns()
                tasks.swap(x, y)
                val now: Int = getAns()
                val diff = now - last
                // 取最小值
                if (diff > 0 && exp(-1.0 * diff / t) * n <= (0 until n).random()) {
                    // 还原操作
                    tasks.swap(x, y)
                }
                t *= delta
            }
        }
        repeat(20) {
            sa()
        }
        return ans
    }
}

// DFS状态压缩，相对较慢，但代码更易理解
//class Solution5856 {
//    fun minSessions(tasks: IntArray, sessionTime: Int): Int {
//        val seen = HashMap<String, Int>()
//        fun dfs(cur: Int, mask: Int): Int {
//            val key = "$cur,$mask"
//            if (key in seen) return seen[key]!!
//            if (mask == (1 shl (tasks.size + 1)) - 1)
//                return 0
//            var ans = 0
//            for (i in tasks.indices) {
//                if ((1 shl i + 1) and mask == 0) {
//                    ans = if (cur + tasks[i] <= sessionTime) {
//                        if (ans == 0) dfs(cur + tasks[i], mask or (1 shl i + 1))
//                        else
//                            minOf(ans, dfs(cur + tasks[i], mask or (1 shl i + 1)))
//                    } else {
//                        if (ans == 0) dfs(tasks[i], mask or (1 shl i + 1)) + 1
//                        else minOf(ans, dfs(tasks[i], mask or (1 shl i + 1)) + 1)
//                    }
//                }
//            }
//            return ans.also {
//                seen[key] = it
//            }
//        }
//        return dfs(0, 0) + 1
//    }
//}

