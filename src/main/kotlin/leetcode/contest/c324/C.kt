package leetcode.contest.c324

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
//    s.isPossible(4, "[[1,2],[2,3],[3,4],[4,1],[1,3]]".toGrid().toList().map { it.toList() }).print()
//    s.isPossible(6, "[[1,2],[1,3],[1,4],[4,5],[5,6]]".toGrid().toList().map { it.toList() }).print()
    s.isPossible(5, "[[1,2],[2,3],[3,4],[4,2],[1,4],[2,5]]".toGrid().toList().map { it.toList() }).print()
    s.isPossible(5, "[[2,3],[5,1],[5,4],[3,4],[2,4],[1,2],[2,5]]".toGrid().toList().map { it.toList() }).print()
}

class SolutionC {
    fun isPossible(n: Int, edges: List<List<Int>>): Boolean {
        val d = IntArray(n)
        val seen = HashSet<String>()
        edges.forEach {
            d[it[0] - 1]++
            d[it[1] - 1]++
            seen.add(it.joinToString(","))
            seen.add(it.reversed().joinToString(","))
        }
        if ((n - 1) % 2 == 1 && d.any { it == n - 1 }) return false
        val set = ArrayList<Int>()
        d.forEachIndexed { index, it ->
            if (it % 2 == 1) set.add(index + 1)
        }
        if (set.size !in arrayOf(0, 2, 4)) return false
        if (set.size == 0) return true
        if (set.size == 2) {
            if ("${set[0]},${set[1]}" !in seen) return true
            if ((1..d.size).any {
                    it != set[0] && it != set[1] && "$it,${set[0]}" !in seen && "$it,${set[1]}" !in seen
                }) return true
            return false
        }
        if (set.size == 4) {
            if ("${set[0]},${set[1]}" !in seen && "${set[2]},${set[3]}" !in seen) return true
            if ("${set[0]},${set[2]}" !in seen && "${set[1]},${set[3]}" !in seen) return true
            if ("${set[0]},${set[3]}" !in seen && "${set[1]},${set[2]}" !in seen) return true
            return false
        }
        return false
    }
}