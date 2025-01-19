package leetcode.contest.c433

import utils.print
import java.util.*


fun main() {
    val s = SolutionB()
    // 24
    s.minMaxSums(intArrayOf(1, 2, 3), 2).print()
    // 22
    s.minMaxSums(intArrayOf(5, 0, 6), 1).print()
    // 12
    s.minMaxSums(intArrayOf(1, 1, 1), 2).print()
}

class SolutionB {
    fun minMaxSums(nums: IntArray, k: Int): Int {
        val mod = 1000000007L
        var ans = 0L
        nums.sort()

        var dp = LongArray(k)
        dp[0] = 1
        for (i in nums.indices) {
            var sum: Long = 1
            // 前i - 1个数中挑选0 到 k - 1个数字
            for (j in minOf(i, k - 1) downTo 1) {
                dp[j] = (dp[j] + dp[j - 1]) % mod
                sum += dp[j]
                sum %= mod
            }
            ans += sum * nums[i] % mod
            ans %= mod
        }

        dp = LongArray(k + 1)
        dp[0] = 1
        for (i in nums.indices.reversed()) {
            var sum: Long = 1
            for (j in minOf(nums.size - i - 1, k - 1) downTo 1) {
                dp[j] = (dp[j] + dp[j - 1]) % mod
                sum += dp[j]
                sum %= mod
            }
            ans += sum * nums[i] % mod
            ans %= mod
        }
        return ans.toInt()
    }

//    fun minMaxSums(nums: IntArray, k: Int): Int {
//        val mod = 1000000007L
//        val count = IntArray(101)
//
//        for (num in nums) {
//            count[num]++
//        }
//
//        var ans = 0L
//
//        // <= 当前值
//        var lessCnt = 0
//
//        // >= 当前值
//        var moreCnt = 0
//
//        // 最大值贡献
//        for (i in 1..100) {
//            lessCnt += count[i]
//            if (count[i] != 0) {
//                for (limit in 1..minOf(k, lessCnt)) {
//                    ans += originC(lessCnt - 1, limit - 1) * i
//                    ans %= mod
//
////                    println("$i 最大值贡献 ${count[i]} * ${originC(lessCnt - 1, limit - 1)} 次  $lessCnt with ${limit - 1}")
//                }
//            }
//        }
//
//        for (i in 100 downTo 1) {
//            moreCnt += count[i]
//            if (count[i] != 0) {
//                for (limit in 1..minOf(k, moreCnt)) {
//                    ans += originC(moreCnt - 1, limit - 1) * i
//                    ans %= mod
//
////                    println("$i 最小值贡献 ${count[i]} * ${originC(moreCnt - 1, limit - 1)} 次  $moreCnt with ${limit - 1}")
//                }
//            }
//        }
//
//        return (ans % mod).toInt()
//    }
}