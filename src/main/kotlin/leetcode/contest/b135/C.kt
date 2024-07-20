package leetcode.contest.b135

import utils.print
import utils.printInt
import kotlin.math.abs

fun main() {
    val s = SolutionC()
    s.minChanges(intArrayOf(0, 1, 2, 3, 3, 6, 5, 4), 6).print()
}

class SolutionC {
    fun minChanges(nums: IntArray, k: Int): Int {
        val n = nums.size
        val halfN = n / 2
        val diff = HashMap<Int, Int>()

        for (i in 0 until n / 2) {
            val c = abs(nums[i] - nums[n - i - 1])
            diff[c] = diff.getOrDefault(c, 0) + 1
        }

        var ans = n
        diff.keys.sortedByDescending { diff[it] }.take(4).forEach { targetDiff ->
            var changes = 0
            for (i in 0 until halfN) {
                val left = nums[i]
                val right = nums[n - i - 1]
                if (abs(left - right) != targetDiff) {
                    if (left + targetDiff in 0..k || left - targetDiff in 0..k) {
                        changes++
                    } else if (right + targetDiff in 0..k || right - targetDiff in 0..k) {
                        changes++
                    } else {
                        changes += 2
                    }
                }
            }
            ans = minOf(ans, changes)
        }
        return ans
    }
}