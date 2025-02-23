package leetcode.contest.c438

class SolutionB {
    fun maxSum(grid: Array<IntArray>, limits: IntArray, k: Int): Long {
        if (k == 0) return 0L
        // val row
        val l = ArrayList<Pair<Int, Int>>()
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                l.add(Pair(grid[i][j], i))
            }
        }
        l.sortBy { -it.first }
        var ans = 0L
        var add = 0
        for (i in l.indices) {
            val row = l[i].second
            if (limits[row] > 0) {
                limits[row]--
                ans += l[i].first
                add++
            }

            if (add == k) return ans
        }
        return ans
    }
}