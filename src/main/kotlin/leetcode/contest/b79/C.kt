package leetcode.contest.b79

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.maximumImportance(5, "[[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]".toGrid()).print()
    s.maximumImportance(5, "[[0,3],[2,4],[1,3]]".toGrid()).print()
    s.maximumImportance(5, "[[0,1]".toGrid()).print()
}

class SolutionC {
    fun maximumImportance(n: Int, roads: Array<IntArray>): Long {
        val map = HashMap<Int, Long>()
        roads.forEach {
            map[it[0]] = map.getOrDefault(it[0], 0L) + 1L
            map[it[1]] = map.getOrDefault(it[1], 0L) + 1L
        }
        val arr = ArrayList<Long>()
        map.forEach {
            arr.add(it.value)
        }
        arr.sort()
        while (arr.size != n) {
            arr.add(0, 0)
        }
        var ans = 0L
        for (i in n downTo 1) {
            ans += arr[i - 1] * i
        }
        return ans
    }
}