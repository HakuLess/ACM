package leetcode.contest.c454

import utils.SortedList
import utils.print
import java.util.*

fun main() {
    val s = SolutionC()
    s.maximumProduct(intArrayOf(-1, -9, 2, 3, -2, -3, 1), 1).print()
    s.maximumProduct(intArrayOf(1, 3, -5, 5, 6, -4), 3).print()
}

class SolutionC {
    fun maximumProduct(nums: IntArray, m: Int): Long {
        var ans = Long.MIN_VALUE
        // 可使用队列
        val a = SortedList<Long>()
        // 中间缓存队列
        val b = LinkedList<Long>()

        for (i in nums.indices) {
            b.addLast(nums[i].toLong())
            if (b.size >= m) {
                a.insert(b.removeFirst())
            }
            if (a.valueList.isNotEmpty()) {
                ans = maxOf(ans, nums[i] * a.valueList.first())
                ans = maxOf(ans, nums[i] * a.valueList.last())
            }
        }

        return ans
    }
}