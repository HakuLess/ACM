package leetcode.contest.c409

class neighborSum(val grid: Array<IntArray>) {

    private val n = grid.size
    private val valueToPosition = mutableMapOf<Int, Pair<Int, Int>>()

    init {
        for (i in 0 until n) {
            for (j in 0 until n) {
                valueToPosition[grid[i][j]] = Pair(i, j)
            }
        }
    }

    fun adjacentSum(value: Int): Int {
        val position = valueToPosition[value] ?: return 0
        val (x, y) = position
        var sum = 0
        val directions = arrayOf(
            Pair(-1, 0),  // 上
            Pair(1, 0),   // 下
            Pair(0, -1),  // 左
            Pair(0, 1)    // 右
        )
        for ((dx, dy) in directions) {
            val newX = x + dx
            val newY = y + dy
            if (newX in 0 until n && newY in 0 until n) {
                sum += grid[newX][newY]
            }
        }
        return sum
    }

    fun diagonalSum(value: Int): Int {
        val position = valueToPosition[value] ?: return 0
        val (x, y) = position
        var sum = 0
        val directions = arrayOf(
            Pair(-1, -1),  // 左上
            Pair(-1, 1),   // 右上
            Pair(1, -1),   // 左下
            Pair(1, 1)     // 右下
        )
        for ((dx, dy) in directions) {
            val newX = x + dx
            val newY = y + dy
            if (newX in 0 until n && newY in 0 until n) {
                sum += grid[newX][newY]
            }
        }
        return sum
    }

}

/**
 * Your neighborSum object will be instantiated and called as such:
 * var obj = neighborSum(grid)
 * var param_1 = obj.adjacentSum(value)
 * var param_2 = obj.diagonalSum(value)
 */