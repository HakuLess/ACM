package leetcode.contest.c272

class SolutionC {
    fun getDescentPeriods(prices: IntArray): Long {
        var ans = 0L
        var cur = 1L
        for (i in prices.indices) {
            if (i != 0 && prices[i] == prices[i - 1] - 1) {
                cur++
            } else {
                cur = 1L
            }
            ans += cur
        }
        return ans
    }
}