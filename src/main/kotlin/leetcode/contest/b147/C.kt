package leetcode.contest.b147

import utils.print
import java.util.*
import kotlin.math.abs


fun main() {
    val s = SolutionC()
    s.longestSubsequence(intArrayOf(16, 6, 3)).print()
    s.longestSubsequence(intArrayOf(1, 8)).print()
}

class SolutionC {

//    fun longestSubsequence(nums: IntArray): Int {
//        val n = nums.size
//        val f = Array(n) { IntArray(300) }
//        Arrays.fill(f[0], 1)
//        val a = IntArray(301) { -1 }
//        a[nums[0]] = 0
//        var ans = 1
//        for (i in 1 until n) {
//            Arrays.fill(f[i], 1)
//            for (j in 0..299) {
//                val x = nums[i] - j
//                val y = nums[i] + j
//                if (x >= 0 && a[x] != -1) {
//                    f[i][j] = maxOf(f[i][j], f[a[x]][j] + 1)
//                }
//                if (y <= 300 && a[y] != -1) {
//                    f[i][j] = maxOf(f[i][j], f[a[y]][j] + 1)
//                }
//            }
//            for (j in 298 downTo 0) {
//                f[i][j] = maxOf(f[i][j], f[i][j + 1])
//            }
//            ans = maxOf(ans, f[i][0])
//            a[nums[i]] = i
//        }
//        return ans
//    }

//    fun longestSubsequence(nums: IntArray): Int {
//        // 最后一个元素值 & 对应最大Diff  For 最大长度
//        val map = HashMap<Pair<Int, Int>, Int>()
//        for (i in nums.indices) {
//            val addMap = HashMap<Pair<Int, Int>, Int>()
//            map.forEach { k, v ->
//                val lst = k.first
//                val maxDiff = k.second
//                val key = Pair(nums[i], abs(nums[i] - lst))
//                if (abs(nums[i] - lst) <= maxDiff) {
//                    addMap[key] = maxOf(1 + v, addMap.getOrDefault(key, 0))
//                }
//            }
//            map[Pair(nums[i], 300)] = 1
//            addMap.forEach { t, u ->
//                map[t] = maxOf(map.getOrDefault(t, 0), u)
//            }
//        }
////        map.forEach { t, u ->
////            println("$t $u")
////        }
//        return map.values.maxOrNull()!!
//    }

//    fun longestSubsequence(nums: IntArray): Int {
//        val n = nums.size
//        if (n <= 1) return n
//        val dp = Array(n) { HashMap<Int, Int>() }
//        var maxLen = 1
//        for (i in 0 until n) {
//            dp[i][Int.MAX_VALUE] = 1
//            for (j in 0 until i) {
//                for ((diff, len) in dp[j]) {
//                    val newDiff = abs(nums[i] - nums[j])
//                    if (diff == Int.MAX_VALUE || newDiff <= diff) {
//                        dp[i][newDiff] = maxOf(dp[i].getOrDefault(newDiff, 0), len + 1)
//                    }
//                }
//            }
//            maxLen = maxOf(maxLen, dp[i].values.maxOrNull() ?: 1)
//        }
//        return maxLen
//    }

    fun longestSubsequence(nums: IntArray): Int {

        val seen = HashMap<String, Int>()
        fun dfs(cur: Int, maxDiff: Int): Int {
            val key = "$cur $maxDiff"
            if (key in seen) return seen[key]!!
            var tmp = 1
            for (j in cur + 1 until nums.size) {
                if (abs(nums[j] - nums[cur]) <= maxDiff) {
                    tmp = maxOf(tmp, 1 + dfs(j, abs(nums[j] - nums[cur])))
                }
            }
            return tmp.also {
                seen[key] = it
            }
        }

        var ans = 2
        for (i in nums.indices) {
            ans = maxOf(ans, dfs(i, 300))
        }
        return ans
    }
}