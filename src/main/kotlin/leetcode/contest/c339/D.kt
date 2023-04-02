package leetcode.contest.c339

import utils.print
import java.util.*
import kotlin.math.abs


fun main() {
    val s = SolutionD()
    // [0,-1,-1,1]
    s.minOperations(4, 0, intArrayOf(), 4).print()
}

class SolutionD {
    fun minOperations(n: Int, p: Int, banned: IntArray, k: Int): IntArray {
        val result = IntArray(n) { -1 }
        result[p] = 0
        // 区分奇数偶数
        val set: Array<TreeSet<Int>> = arrayOf(TreeSet(), TreeSet())
        for (i in 0 until n) {
            set[i % 2].add(i)
        }
        set[p % 2].remove(p)
        for (i in banned) {
            set[i % 2].remove(i)
        }
        val deque = ArrayDeque(listOf(p))
        while (!deque.isEmpty()) {
            val poll = deque.poll()
            val i = abs(poll - k + 1)
            var j = set[i % 2].ceiling(i)
            while (j != null && j < n - abs(n - poll - k)) {
                deque.offer(j)
                result[j] = result[poll] + 1
                set[i % 2].remove(j)
                j = set[i % 2].higher(j)
            }
        }
        return result
    }
}