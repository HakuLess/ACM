package leetcode.contest.c400

import utils.print
import java.util.*
import kotlin.collections.HashSet
import kotlin.math.abs

fun main() {
    val s = SolutionD()
    s.minimumDifference(intArrayOf(5, 13, 90, 92, 49), 10).print()
}

class SolutionD {
    fun minimumDifference(nums: IntArray, k: Int): Int {
        val seen = HashSet<String>()

        // index & 当前值
        val queue: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
        for (i in nums.indices) {
            queue.offer(Pair(i, nums[i]))
        }

        var ans = Int.MAX_VALUE

        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val (index, v) = queue.poll()
                val curV = v and nums[index]

                ans = minOf(ans, abs(curV - k))
                ans = minOf(ans, abs(v - k))

                if (curV == k) {
                    return 0
                }

                val key = "${index + 1}_$curV"
                if (index + 1 in nums.indices && key !in seen) {
                    queue.offer(Pair(index + 1, curV))
                    seen.add(key)
                }
            }
        }

        return ans
    }
}