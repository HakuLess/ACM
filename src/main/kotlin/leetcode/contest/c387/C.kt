package leetcode.contest.c387

class SolutionC {
    fun minimumOperationsToWriteY(grid: Array<IntArray>): Int {
        val mapY = HashMap<Int, Int>()
        val mapNotY = HashMap<Int, Int>()

        val n = grid.size
        for (i in 0 until n) {
            for (j in 0 until n) {
                var inY = false
                if (i <= n / 2) {
                    if (i == j || i + j == n - 1) {
                        inY = true
                    }
                } else {
                    if (j == n / 2) {
                        inY = true
                    }
                }

                val item = grid[i][j]
                if (inY) {
                    mapY[item] = mapY.getOrDefault(item, 0) + 1
                } else {
                    mapNotY[item] = mapNotY.getOrDefault(item, 0) + 1
                }
            }
        }

        val countY = n + n / 2
        val countNotY = n * n - countY

        var ans = Int.MAX_VALUE
        for (a in 0..2) {
            for (b in 0..2) {
                if (a == b) continue
                val tmp1 = mapY.getOrDefault(a, 0)
                val tmp2 = mapNotY.getOrDefault(b, 0)

                val tmp = (countY - tmp1) + (countNotY - tmp2)
                ans = minOf(ans, tmp)
            }
        }
        return ans
    }
}