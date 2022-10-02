package leetcode.contest.c313

class SolutionB {
    fun maxSum(grid: Array<IntArray>): Int {
        val n = grid.size
        val m = grid[0].size
        var ans = 0
        for (i in 1 until n - 1) {
            for (j in 1 until m - 1) {
                // 以i,j为中心
                var tmp = 0
                tmp += grid[i][j]
                tmp += grid[i - 1][j - 1]
                tmp += grid[i - 1][j]
                tmp += grid[i - 1][j + 1]
                tmp += grid[i + 1][j - 1]
                tmp += grid[i + 1][j]
                tmp += grid[i + 1][j + 1]

                ans = maxOf(ans, tmp)
            }
        }
        return ans
    }
}