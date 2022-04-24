package leetcode.contest.c290

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.fullBloomFlowers("[[1,6],[3,7],[9,12],[4,13]]".toGrid(), intArrayOf(2, 3, 7, 11)).print()
}

class SolutionD {
    fun fullBloomFlowers(flowers: Array<IntArray>, persons: IntArray): IntArray {
        val map = HashMap<Int, Int>()
        var index = 0
        flowers.forEach {
            if (it[0] !in map.keys) {
                map[it[0]] = index++
            }
            if (it[1] !in map.keys) {
                map[it[1]] = index++
            }
        }
        val n = map.keys.size
//        val root = SegmentAddTree(n * 2)
//        root.build(1, 1, n)
//        flowers.forEach {
//            root.update(1, it[0], it[1], 1)
//        }
        val ans = IntArray(persons.size)
        for (i in persons.indices) {
//            ans[i] = root.query(1, i, i, 0).toInt()
        }
        return ans
    }
}