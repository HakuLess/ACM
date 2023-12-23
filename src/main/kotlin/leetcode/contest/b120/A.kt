package leetcode.contest.b120

import utils.print

fun main() {
    val s = SolutionA()
    s.incremovableSubarrayCount(intArrayOf(1, 2, 3, 4)).print()
    s.incremovableSubarrayCount(intArrayOf(6, 5, 7, 8)).print()
    s.incremovableSubarrayCount(intArrayOf(8, 7, 6, 6)).print()
}

class SolutionA {
    fun incremovableSubarrayCount(nums: IntArray): Int {
        var ans = 0
        for (i in nums.indices) {
            for (j in i + 1..nums.size) {
                val l = ArrayList<Int>()
                l.addAll(nums.toTypedArray())
                val a = l.subList(0, i)
                val b = l.subList(j, l.size)
                val left = ArrayList<Int>()
                left.addAll(a)
                left.addAll(b)
                if (left.toHashSet().size == left.size && left.joinToString() == left.sorted().joinToString()) {
//                    println("remove $i..$j with ${left.joinToString()}")
                    ans++
                }
            }
        }
        return ans
    }
}