package leetcode.normal

import utils.TypedUFS
import utils.dir4
import utils.print
import utils.toGrid

fun main() {
    val s = Solution305()
    s.numIslands2(3, 3, "[[0,0],[0,1],[1,2],[2,1]]".toGrid()).joinToString().print()
    s.numIslands2(3, 3, "[[0,1],[1,2],[2,1],[1,0],[0,2],[0,0],[1,1]]".toGrid()).joinToString().print()
}

class Solution305 {
    fun numIslands2(m: Int, n: Int, positions: Array<IntArray>): List<Int> {
        val ufs = TypedUFS<Pair<Int, Int>>(positions.size)
        val matrix = Array<IntArray>(m) { IntArray(n) }
        val ans = ArrayList<Int>()
        var cur = 0
        positions.forEach {
            val x = it[0]
            val y = it[1]
            if (matrix[x][y] == 1) {
                ans.add(cur)
                return@forEach
            }
            matrix[x][y] = 1
            cur++
            dir4.forEach {
                val nextX = x + it[0]
                val nextY = y + it[1]
                if (nextX in 0 until m && nextY in 0 until n) {
                    if (matrix[nextX][nextY] == 1) {
                        if (ufs.union(Pair(nextX, nextY), Pair(x, y))) {
                            cur--
                        }
                    }
                }
            }
            ans.add(cur)
        }
        return ans
    }
}