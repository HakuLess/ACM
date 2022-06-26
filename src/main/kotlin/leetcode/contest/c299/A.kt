package leetcode.contest.c299

class SolutionA {
    fun checkXMatrix(grid: Array<IntArray>): Boolean {
        val n = grid.size
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (i + j == n - 1 || i == j) {
                    if (grid[i][j] == 0) {
                        return false
                    }
                } else {
                    if (grid[i][j] != 0) {
                        return false
                    }
                }
            }
        }
        return true
    }
}