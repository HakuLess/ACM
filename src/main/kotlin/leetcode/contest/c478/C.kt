package leetcode.contest.c478

import utils.print
import utils.printInt

fun main() {
    val s = SolutionC()
    // 1
    s.minMirrorPairDistance(intArrayOf(120, 21)).print()
    // -1
    s.minMirrorPairDistance(intArrayOf(21, 120)).print()

    s.minMirrorPairDistance(intArrayOf(12, 21, 12)).print()
}

class SolutionC {
    fun minMirrorPairDistance(nums: IntArray): Int {
        val seen = HashMap<Long, Int>()
        var ans = Int.MAX_VALUE

        for (j in nums.indices) {
            val v = nums[j].toLong()
            val rev = v.toString().reversed().toLong()

            if (v in seen) {
                val i = seen[v]!!
                ans = minOf(ans, j - i)
            }

            seen[rev] = j
//            seen.printInt()
        }

        return if (ans == Int.MAX_VALUE) -1 else ans
    }
}