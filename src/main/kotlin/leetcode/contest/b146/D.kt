package leetcode.contest.b146

import utils.C
import utils.originC

// Not Finished
class SolutionD {
    fun subsequencesWithMiddleMode(nums: IntArray): Int {
        val map = HashMap<Int, Int>()
        val total = nums.size
        val mod = 1000000007L
        nums.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }

        var ans = 0L
        map.keys.forEach { key ->
            val cnt = map[key]!!
            // 取5个相同给的
            if (cnt >= 5) {
                ans += originC(cnt, 5)
            }

            if (cnt >= 4) {

            }
        }

        return (ans % mod).toInt()
    }
}