package leetcode.contest.b102

class SolutionA {
    fun findColumnWidth(grid: Array<IntArray>): IntArray {
        val m = grid.size
        val n = grid[0].size
        val ans = IntArray(n)
        for (i in 0 until n) {
            for (j in 0 until m) {
                ans[i] = maxOf(ans[i], grid[j][i].toString().length)
            }
        }
        return ans
    }
}