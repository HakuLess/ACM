package leetcode.contest.c332

import java.util.*

class SolutionA {
    fun findTheArrayConcVal(nums: IntArray): Long {
        var ans = 0L
        val l = LinkedList<Int>()
        l.addAll(nums.toList())
        while (l.isNotEmpty()) {
            val a = l.removeFirst().toString()
            val b = if (l.isEmpty()) "" else l.removeLast()?.toString()
            ans += "$a$b".toLong()
        }
        return ans
    }
}