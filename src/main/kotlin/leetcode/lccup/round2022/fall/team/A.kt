package leetcode.lccup.round2022.fall.team

class SolutionA {
    fun minNumBooths(demand: Array<String>): Int {
        val ans = IntArray(26)
        demand.forEach {
            for (i in 0 until 26) {
                ans[i] = maxOf(ans[i], it.count { it - 'a' == i })
            }
        }
        return ans.sum()
    }
}