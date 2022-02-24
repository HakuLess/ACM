package leetcode.normal

class Solution265 {
    fun minCostII(costs: Array<IntArray>): Int {
        val k = costs[0].size
        var ans = costs[0]

        fun getMin(skip: Int): Int {
            var min = Int.MAX_VALUE
            for (i in ans.indices) {
                if (i == skip) continue
                min = minOf(min, ans[i])
            }
            return min
        }

        for (i in 1 until costs.size) {
            val next = IntArray(k)
            for (j in next.indices) {
                next[j] = costs[i][j] + getMin(j)
            }
            ans = next
        }
//        return ans.min()!!
        return ans.minOrNull()!!
    }
}