package leetcode.contest.b70

class SolutionA {
    fun minimumCost(cost: IntArray): Int {
        cost.sortDescending()
        var ans = 0
        for (i in cost.indices) {
            if (i % 3 == 0 || i % 3 == 1) {
                ans += cost[i]
            }
        }
        return ans
    }
}