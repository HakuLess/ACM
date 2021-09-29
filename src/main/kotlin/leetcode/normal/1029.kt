package leetcode.normal

class Solution1029 {
    fun twoCitySchedCost(costs: Array<IntArray>): Int {
        costs.sortBy { it[0] - it[1] }
        var ans = 0
        val n = costs.size / 2
        for (i in costs.indices) {
            ans += if (i < n) costs[i][0]
            else costs[i][1]
        }
        return ans
    }
}