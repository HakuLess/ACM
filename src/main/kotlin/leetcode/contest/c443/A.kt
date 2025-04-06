package leetcode.contest.c443

class SolutionA {
    fun minCosts(cost: IntArray): IntArray {
        val n = cost.size
        val answer = IntArray(n)
        var curMin = Int.MAX_VALUE
        for (i in 0 until n) {
            curMin = minOf(curMin, cost[i])
            answer[i] = curMin
        }
        return answer
    }
}