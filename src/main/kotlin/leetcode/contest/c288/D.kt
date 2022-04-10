package leetcode.contest.c288

import utils.biMax
import utils.print
import java.util.*

fun main() {
    val s = SolutionD()
    // 14
//    s.maximumBeauty(intArrayOf(1, 3, 1, 1), 7, 6, 12, 1).print()
    // 30
//    s.maximumBeauty(intArrayOf(2, 4, 5, 3), 10, 5, 2, 6).print()
    // 47
//    s.maximumBeauty(intArrayOf(5, 19, 1, 1, 6, 10, 18, 12, 20, 10, 11), 6, 20, 3, 11).print()
    // 54
    s.maximumBeauty(intArrayOf(8, 2), 24, 18, 6, 3).print()
}

class SolutionD {
    fun maximumBeauty(flowers: IntArray, newFlowers: Long, target: Int, full: Int, partial: Int): Long {
        var ans = 0L
        flowers.sortDescending()
        for (i in flowers.indices) {
            // 当前值填满，其余值尽可能最大
        }
        return ans
    }
}