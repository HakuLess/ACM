package leetcode.contest.b60

import utils.print
import utils.toGrid

fun main() {
    val s = Solution5847()
    s.findFarmland("[[1,1,0,0,0,1],[1,1,0,0,0,0]]".toGrid()).print()
}

class Solution5847 {
    fun findFarmland(land: Array<IntArray>): Array<IntArray> {
        val n = land.size
        val m = land[0].size

        val ans = ArrayList<IntArray>()
        fun getEdge(i: Int, j: Int) {
            var curI = i
            var curJ = j
            while (curI in 0 until n && land[curI][curJ] == 1) {
                curI++
            }
            curI--
            while (curJ in 0 until m && land[curI][curJ] == 1) {
                curJ++
            }
            curJ--
            ans.add(intArrayOf(i, j, curI, curJ))
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (land[i][j] == 1) {
                    if (i > 0 && land[i - 1][j] == 1) continue
                    if (j > 0 && land[i][j - 1] == 1) continue
                    getEdge(i, j)
                }
            }
        }
        return ans.toTypedArray()
    }
}