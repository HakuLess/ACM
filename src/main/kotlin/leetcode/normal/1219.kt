package leetcode.normal

import utils.dir4
import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = Solution1219()
    s.getMaximumGold("[[0,6,0],[5,8,7],[0,9,0]]".toGrid()).print()
    s.getMaximumGold("[[1,0,7,0,0,0],[2,0,6,0,1,0],[3,5,6,7,4,2],[4,3,1,0,2,0],[3,0,5,0,20,0]]".toGrid()).print()
}

class Solution1219 {
    fun getMaximumGold(grid: Array<IntArray>): Int {
        var ans = 0
        fun dfs(i: Int, j: Int, seen: HashSet<Pair<Int, Int>>, cur: Int) {
            ans = maxOf(ans, cur)
            for (dir in dir4) {
                val nX = i + dir[0]
                val nY = j + dir[1]
                if (nX in grid.indices && nY in grid[0].indices && grid[nX][nY] != 0 && Pair(nX, nY) !in seen) {
                    seen.add(Pair(nX, nY))
                    dfs(nX, nY, seen, cur + grid[nX][nY])
                    seen.remove(Pair(nX, nY))
                }
            }
        }
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] != 0)
                    dfs(i, j, hashSetOf(Pair(i, j)), grid[i][j])
            }
        }
        return ans
    }
}