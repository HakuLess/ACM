package leetcode.contest.c448

import utils.print

fun main() {
    val s = SolutionD()
    s.magicalSum(5, 5, intArrayOf(1, 10, 100, 10000, 1000000)).print()
}

// TODO Not Finished
class SolutionD {
    fun magicalSum(M: Int, K: Int, nums: IntArray): Int {

        val mod = 1_000_000_007L

        val n = nums.size

        val dp = Array(n + 1) { Array(M + 1) { Array(M + 1) { LongArray(M + 1) { -1L } } } }

        // 预计算排列数 P[r][x] = r! / (r-x)! mod MOD
        val perm = Array(M + 1) { LongArray(M + 1) { 0L } }
        perm[0][0] = 1
        for (r in 1..M) {
            perm[r][0] = 1
            var p = 1L
            for (x in 1..r) {
                p = (p * (r - x + 1)) % mod
                perm[r][x] = p
            }
        }

        // dfs(j, rem, carry, bits) 返回：在处理到 nums[j] 时的所有序列累积「数组乘积之和」 mod
        fun dfs(j: Int, rem: Int, carry: Int, bits: Int): Long {
            if (bits > K) return 0L        // 已超位数
            if (j == n) {
                // 用完所有数字，且恰好用尽 rem==0 且无多余进位 且 bits==K
                return if (rem == 0 && carry == 0 && bits == K) 1L else 0L
            }
            val memo = dp[j][rem][carry]
            if (memo[bits] >= 0) return memo[bits]

            var ans = 0L
            // 尝试在序列中选择 x 次下标 j
            for (x in 0..rem) {
                // 新的乘积系数： nums[j]^x * 排列数 P(rem, x)
                var coef = perm[rem][x]
                if (x > 0) {
                    // 快速幂算 nums[j]^x mod
                    var base = nums[j].toLong() % mod
                    var exp = x
                    var pw = 1L
                    while (exp > 0) {
                        if (exp and 1 == 1) pw = pw * base % mod
                        base = base * base % mod
                        exp = exp shr 1
                    }
                    coef = coef * pw % mod
                }
                // 处理二进制当前位：carry_in + x
                val total = carry + x
                val bit = total and 1
                val newCarry = total shr 1
                ans = (ans + coef * dfs(j + 1, rem - x, newCarry, bits + bit) % mod) % mod
            }

            memo[bits] = ans
            return ans
        }

        return dfs(0, M, 0, 0).toInt()
    }
}