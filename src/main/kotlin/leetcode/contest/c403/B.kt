package leetcode.contest.c403

class SolutionB {
    fun minimumArea(grid: Array<IntArray>): Int {
        val rows = grid.size
        val cols = grid[0].size
        var top = rows
        var bottom = -1
        var left = cols
        var right = -1

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (grid[i][j] == 1) {
                    if (i < top) top = i
                    if (i > bottom) bottom = i
                    if (j < left) left = j
                    if (j > right) right = j
                }
            }
        }

        val height = bottom - top + 1
        val width = right - left + 1
        return height * width
    }
}