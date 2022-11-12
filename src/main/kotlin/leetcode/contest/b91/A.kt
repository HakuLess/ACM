package leetcode.contest.b91

import java.util.*
import kotlin.collections.HashSet

class SolutionA {
    fun distinctAverages(nums: IntArray): Int {
        val set = HashSet<Int>()
        val cur = LinkedList<Int>()
        cur.addAll(nums.sorted())
        while (cur.isNotEmpty()) {
            val a = cur.removeFirst()
            val b = cur.removeLast()
            set.add(a + b)
        }
        return set.size
    }
}