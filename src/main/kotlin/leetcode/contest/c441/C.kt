package leetcode.contest.c441

class SolutionC {
    fun minZeroArray(nums: IntArray, queries: Array<IntArray>): Int {
        if (nums.all { it == 0 }) return 0

        fun canZero(k: Int): Boolean {
            for (i in nums.indices) {
                val target = nums[i]
                val values = mutableListOf<Int>()
                for (j in 0 until k) {
                    val (l, r, v) = queries[j]
                    if (i in l..r) {
                        values.add(v)
                    }
                }
                val dp = BooleanArray(target + 1)
                dp[0] = true
                for (v in values) {
                    for (s in target downTo v) {
                        if (dp[s - v]) dp[s] = true
                    }
                }
                if (!dp[target]) return false
            }
            return true
        }

        for (i in queries.indices) {
            if (canZero(i + 1)) {
                return i + 1
            }
        }
        return -1
    }
}