package leetcode.contest.c446

class SolutionC {
    fun resultArray(nums: IntArray, k: Int): LongArray {

        val ans = LongArray(k) { 0L }
        val dp = LongArray(k) { 0L }

        for (num in nums) {
            val newDp = LongArray(k) { 0L }
            val mod = num % k
            newDp[mod]++

            for (x in 0 until k) {
                // 计算历史贡献
                val y = (x * mod) % k
                newDp[y] += dp[x]
            }

            for (i in 0 until k) {
                ans[i] += newDp[i]
                dp[i] = newDp[i]
            }
        }

        return ans
    }
}