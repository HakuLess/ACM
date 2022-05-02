package leetcode.contest.c290

import utils.SortedList
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.fullBloomFlowers("[[1,6],[3,7],[9,12],[4,13]]".toGrid(), intArrayOf(2, 3, 7, 11)).print()
    s.fullBloomFlowers("[[1,10],[3,3]]".toGrid(), intArrayOf(3, 3, 2)).print()
}

class SolutionD {
    fun fullBloomFlowers(flowers: Array<IntArray>, persons: IntArray): IntArray {

        flowers.sortBy { it[0] }
        val n = persons.size
        val ids = IntRange(0, n - 1).toList().toTypedArray()
        // 对id进行排序
        ids.sortBy { persons[it] }

        val cur = SortedList<Int>()
        val ans = IntArray(persons.size)
        var i = 0
        for (id in ids) {
            while (i in flowers.indices && flowers[i][0] <= persons[id]) {
                // 增加纵坐标
                cur.insert(flowers[i][1])
                i++
            }
            ans[id] = cur.largerThanAndEqual(persons[id])
        }
        return ans
    }
}