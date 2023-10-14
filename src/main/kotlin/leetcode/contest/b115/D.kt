package leetcode.contest.b115

import utils.print


fun main() {
    val s = SolutionD()
//    s.countSubMultisets(listOf(1, 2, 2, 3), 6, 6).print()
//    s.countSubMultisets(listOf(2, 1, 4, 2, 7), 1, 5).print()
    s.countSubMultisets(listOf(1, 2, 1, 3, 5, 2), 3, 5).print()
}

class SolutionD {
    fun countSubMultisets(nums: List<Int>, l: Int, r: Int): Int {
        val mod = 1000000007L
        val map = HashMap<Int, Int>()
        for (num in nums) {
            map[num] = map.getOrDefault(num, 0) + 1
        }
        var curSum = 0
        var dp = LongArray(1)
        dp[0] = 1
        map.keys.forEach { key ->
            val curCnt = map[key]!!
            val newSum = curSum + key * curCnt
            val newDp = LongArray(newSum + 1)
            for (i in 0..curSum) {
                for (j in 0..curCnt) {
                    newDp[i + j * key] += dp[i]
                    newDp[i + j * key] %= mod
                }
            }
            dp = newDp
            curSum = newSum
        }
        var ans = 0L
        for (i in l..minOf(dp.size - 1, r)) {
            ans = (ans + dp[i]) % mod
        }
        return ans.toInt()
    }
}