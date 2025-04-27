package leetcode.contest.c447

import utils.print

fun main() {
    val s = SolutionC()
    s.concatenatedDivisibility(intArrayOf(3, 12, 45), 5).print()
    s.concatenatedDivisibility(intArrayOf(10, 5), 10).print()
}

class SolutionC {
    fun concatenatedDivisibility(nums: IntArray, k: Int): IntArray {
        val n = nums.size

        val len = IntArray(n)
        val modArr = IntArray(n)
        for ((i, x) in nums.withIndex()) {
            len[i] = x.toString().length
            modArr[i] = x % k
        }


        val maxLen = len.maxOrNull()!!
        val pow10 = IntArray(maxLen + 1) { 1 }
        for (d in 1..maxLen) {
            pow10[d] = (pow10[d - 1] * 10) % k
        }

        val total = 1 shl n
        // -1 默认值
        // 1 能除尽
        val dp = Array(total) { IntArray(k) { -1 } }

        fun dfs(mask: Int, rem: Int): Boolean {

            // 余数为0，符合条件，回抛true
            if (mask == (total - 1)) {
                return rem == 0
            }
            if (dp[mask][rem] != -1) return dp[mask][rem] == 1

            for (i in 0 until n) {
                if ((mask and (1 shl i)) == 0) {
                    val newRem = (rem * pow10[len[i]] + modArr[i]) % k
                    if (dfs(mask or (1 shl i), newRem)) {
                        dp[mask][rem] = 1
                        return true
                    }
                }
            }
            // 已计算过，无法除尽，减少后续冗余计算
            dp[mask][rem] = 0
            return false
        }

//        dfs(0, 0)

        val ans = ArrayList<Int>()

        var mask = 0
        var rem = 0

        // nums升序遍历，第一个找到的符合预期
        val ids = nums.indices.sortedBy { nums[it] }
        for (step in 0 until n) {
            for (i in ids) {
                // 未被 选中过
                if ((mask and (1 shl i)) == 0) {
                    val newRem = (rem * pow10[len[i]] + modArr[i]) % k
                    if (dfs(mask or (1 shl i), newRem)) {
                        ans.add(nums[i])
                        mask = mask or (1 shl i)
                        rem = newRem
                        break
                    }
                }
            }
        }
        return ans.toIntArray()
    }
}