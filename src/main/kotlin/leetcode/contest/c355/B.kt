package leetcode.contest.c355

import utils.print

fun main() {
    val s = SolutionB()
    s.maxArrayValue(intArrayOf(2, 3, 7, 9, 3)).print()
}

class SolutionB {
    fun maxArrayValue(nums: IntArray): Long {
        val l = ArrayList<Long>()
        var ans = nums[0].toLong()
        l.addAll(nums.map { it.toLong() })
        var right = l.size - 1
        var left = right - 1
        while (left in l.indices) {
            ans = maxOf(ans, l[left])
            if (l[right] >= l[left]) {
                val c = l[right] + l[left]
                l.removeAt(right)
                l.removeAt(left)
                l.add(left, c)
                ans = maxOf(ans, c)
            }
            right--
            left--
            l.joinToString().print()
        }
        return ans
    }
}