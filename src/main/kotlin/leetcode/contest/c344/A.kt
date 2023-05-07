package leetcode.contest.c344

import utils.print

fun main() {
    val s = SolutionA()
    s.distinctDifferenceArray(intArrayOf(1, 2, 3, 4, 5)).print()
}

class SolutionA {
    fun distinctDifferenceArray(nums: IntArray): IntArray {
        val ans = ArrayList<Int>()
        val l = ArrayList<Int>()
        l.addAll(nums.toList())
        for (i in nums.indices) {
            val a = l.subList(0, i + 1)
            val b = l.subList(i + 1, l.size)
            ans.add(a.toHashSet().size - b.toHashSet().size)
        }
        return ans.toIntArray()
    }
}