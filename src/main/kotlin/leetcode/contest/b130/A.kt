package leetcode.contest.b130

class SolutionA {
    fun satisfiesConditions(grid: Array<IntArray>): Boolean {
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (i + 1 in grid.indices && grid[i][j] != grid[i + 1][j]) return false
                if (j + 1 in grid[0].indices && grid[i][j] == grid[i][j + 1]) return false
            }
        }
        return true
    }
}