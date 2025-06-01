package leetcode.contest.c452

class SolutionB {
    fun minAbsDiff(grid: Array<IntArray>, k: Int): Array<IntArray> {
        val m = grid.size
        val n = grid[0].size
        val ans = Array(m - k + 1) { IntArray(n - k + 1) }

        for (i in 0..m - k) {
            for (j in 0..n - k) {
                val set = HashSet<Int>()
                for (x in i until i + k) {
                    for (y in j until j + k) {
                        set.add(grid[x][y])
                    }
                }

                if (set.size == 1) {
                    ans[i][j] = 0
                } else {
                    val sorted = set.sorted()
                    var minDiff = Int.MAX_VALUE
                    for (z in 1 until sorted.size) {
                        minDiff = minOf(minDiff, sorted[z] - sorted[z - 1])
                    }
                    ans[i][j] = minDiff
                }
            }
        }

        return ans
    }
}