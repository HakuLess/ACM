package leetcode.contest.c331

import utils.TypedUFS
import utils.print
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = SolutionC()
    s.minCapability(intArrayOf(2, 3, 5, 9), 2).print()
    s.minCapability(intArrayOf(2, 7, 9, 3, 1), 2).print()
}

class SolutionC {
    fun minCapability(nums: IntArray, k: Int): Int {
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        val ufs = TypedUFS<Int>(nums.size + 1)
        val set = HashSet<Int>()
        nums.forEachIndexed { index, it ->
            pq.offer(Pair(it, index))
        }
        var cur = k
        while (pq.isNotEmpty()) {
            val (v, index) = pq.poll()
            set.add(index)
            var left = 0
            if (index - 1 in set) {
                left = ufs.count[ufs.typedFind(index - 1)]
                ufs.union(index - 1, index)
            }
            var right = 0
            if (index + 1 in set) {
                right = ufs.count[ufs.typedFind(index + 1)]
                ufs.union(index + 1, index)
            }
            cur += (right + 1) / 2 + (left + 1) / 2
            ufs.count[ufs.typedFind(index)].let {
                cur -= (it + 1) / 2
            }
            if (cur == 0) return v
        }
        return 0
    }
}