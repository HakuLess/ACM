package leetcode.contest.c430

class SolutionA {
    fun minimumOperations(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        var operations = 0

        for (col in 0 until n) {
            for (row in 1 until m) {
                if (grid[row][col] <= grid[row - 1][col]) {
                    val increment = grid[row - 1][col] - grid[row][col] + 1
                    operations += increment
                    grid[row][col] += increment
                }
            }
        }

        return operations
    }
}