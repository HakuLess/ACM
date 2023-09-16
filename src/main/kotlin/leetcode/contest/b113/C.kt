package leetcode.contest.b113

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.countPairs("[[1,2],[4,2],[1,3],[5,2]]".toGrid().map { it.toList() }, 5).print()
    s.countPairs("[[1,3],[1,3],[1,3],[1,3],[1,3]]".toGrid().map { it.toList() }, 0).print()
}

class SolutionC {
    fun countPairs(coordinates: List<List<Int>>, k: Int): Int {
        val map = HashMap<Pair<Int, Int>, Int>()
        var ans = 0

        coordinates.map { Pair(it[0], it[1]) }.forEach { p ->
            repeat(k + 1) {
                val a = p.first xor it
                val b = p.second xor (k - it)
                ans += map.getOrDefault(Pair(a, b), 0)
            }
            map[p] = map.getOrDefault(p, 0) + 1
        }
        return ans
    }
}