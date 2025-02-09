package leetcode.contest.c436

class SolutionA {
    fun sortMatrix(grid: Array<IntArray>): Array<IntArray> {
        val n = grid.size
        val diagonals = mutableMapOf<Int, MutableList<Int>>()

        for (i in 0 until n) {
            for (j in 0 until n) {
                diagonals.getOrPut(i - j) { mutableListOf() }.add(grid[i][j])
            }
        }

        for ((key, list) in diagonals) {
            if (key >= 0) {
                list.sortDescending()
            } else {
                list.sort()
            }
        }

        for (i in 0 until n) {
            for (j in 0 until n) {
                grid[i][j] = diagonals[i - j]!!.removeAt(0)
            }
        }

        return grid
    }
}