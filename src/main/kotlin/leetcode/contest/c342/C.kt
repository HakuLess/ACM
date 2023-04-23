package leetcode.contest.c342

import utils.MultiSet
import utils.SortedList
import utils.print

fun main() {
    val s = SolutionC()
    s.getSubarrayBeauty(intArrayOf(1, -1, -3, -2, 3), 3, 2).print()
}

class SolutionC {
    fun getSubarrayBeauty(nums: IntArray, k: Int, x: Int): IntArray {
        val l = SortedList<Int>()
        val ans = ArrayList<Int>()
        for (i in nums.indices) {
            l.insert(nums[i])
            if (l.valueList.size > k) {
                l.remove(nums[i - k])
            }
            if (l.valueList.size == k) {
                ans.add(minOf(l.valueList[x - 1], 0))
            }
        }
        return ans.toIntArray()
    }
}