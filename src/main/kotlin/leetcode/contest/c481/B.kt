package leetcode.contest.c481

class SolutionB {
    fun minCost(s: String, cost: IntArray): Long {
        val n = s.length
        val sumCost = LongArray(26)
        var totalCost = 0L

        for (i in 0 until n) {
            val idx = s[i] - 'a'
            sumCost[idx] += cost[i].toLong()
            totalCost += cost[i].toLong()
        }

        var ans = Long.MAX_VALUE
        for (c in 0 until 26) {
            ans = minOf(ans, totalCost - sumCost[c])
        }

        return ans
    }
}