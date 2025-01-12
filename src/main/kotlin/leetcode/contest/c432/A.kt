package leetcode.contest.c432

class SolutionA {
    fun zigzagTraversal(grid: Array<IntArray>): List<Int> {
        val result = mutableListOf<Int>()
        for (i in grid.indices) {
            val row = if (i % 2 == 0) grid[i] else grid[i].reversedArray()
            val start = if (i % 2 == 0 || grid[i].size % 2 == 0) 0 else 1
            for (j in start..row.lastIndex step 2) {
                result.add(row[j])
            }
        }
        return result
    }
}