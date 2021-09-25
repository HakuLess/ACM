package leetcode.lccup.b

import utils.dir4
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionB()
    s.bicycleYard(intArrayOf(0, 0), "[[0,0],[0,0]]".toGrid(), "[[0,0],[0,0]]".toGrid()).print()
    s.bicycleYard(intArrayOf(1, 1), "[[5,0],[0,6]]".toGrid(), "[[0,6],[7,0]]".toGrid()).print()
}

class SolutionB {
    fun bicycleYard(position: IntArray, terrain: Array<IntArray>, obstacle: Array<IntArray>): Array<IntArray> {
        val seen = HashSet<Triple<Int, Int, Int>>()
        val ans = ArrayList<IntArray>()
        fun dfs(x: Int, y: Int, s: Int) {
//            println("$x $y $s")
            if (Triple(x, y, s) in seen) return
            seen.add(Triple(x, y, s))
            if (s < 1) return
            if (s == 1) ans.add(intArrayOf(x, y))
            for (it in dir4) {
                val next = intArrayOf(x + it[0], y + it[1])
                if (next[0] !in terrain.indices || next[1] !in terrain[0].indices) continue
                dfs(next[0], next[1], s + terrain[x][y] - terrain[next[0]][next[1]] - obstacle[next[0]][next[1]])
            }
        }
        dfs(position[0], position[1], 1)
        ans.removeAt(0)
        ans.sortWith(compareBy({ it[0] }, { it[1] }))
        return ans.toTypedArray()
    }
}