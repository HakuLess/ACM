package leetcode.contest.c306

class SolutionA {
    fun largestLocal(grid: Array<IntArray>): Array<IntArray> {
        val n = grid.size
        val ans = Array<IntArray>(n - 2) { IntArray(n - 2) }
        for (i in 0 until n - 2) {
            for (j in 0 until n - 2) {
                ans[i][j] = arrayOf(
                    grid[i][j],
                    grid[i + 1][j],
                    grid[i + 2][j],
                    grid[i][j + 1],
                    grid[i][j + 2],
                    grid[i + 1][j + 1],
                    grid[i + 1][j + 2],
                    grid[i + 2][j + 1],
                    grid[i + 2][j + 2]
//                ).max()!!
                ).maxOrNull()!!
            }
        }
        return ans
    }
}