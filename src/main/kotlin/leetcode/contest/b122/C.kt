package leetcode.contest.b122

import utils.SortedList
import utils.print
import java.util.*

fun main() {
    val s = SolutionC()
    // 2
    s.minimumArrayLength(intArrayOf(4, 4, 4)).print()
}

class SolutionC {
    fun minimumArrayLength(nums: IntArray): Int {
        nums.sort()
        var c = nums[0]
        for (i in 1 until nums.size) {
            if (nums[i] % c != 0) {
                c = minOf(nums[i] % c, c)
            }
        }
        return maxOf(1, (nums.count { it == c } + 1) / 2)
    }
}