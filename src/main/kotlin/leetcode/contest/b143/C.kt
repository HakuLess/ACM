package leetcode.contest.b143

import utils.print
import java.util.*


fun main() {
    val s = SolutionC()
//    s.maxFrequency(intArrayOf(14, 67, 36, 118, 4), 62, 3).print()
    // 3
//    s.maxFrequency(intArrayOf(58, 80, 5), 58, 2).print()
    // 1
//    s.maxFrequency(intArrayOf(1, 90), 76, 1).print()
    // 2
    s.maxFrequency(intArrayOf(56, 95, 131, 49, 147), 31, 2).print()
}

class SolutionC {
    fun maxFrequency(nums: IntArray, k: Int, numOperations: Int): Int {

        nums.sort()

        var ans = 1
        val n = nums.size
        val cnt = HashMap<Int, Int>()
        for (num in nums) {
            cnt.merge(num, 1) { a: Int, b: Int -> Integer.sum(a, b) }
        }

        // 固定数
        var l = -1
        var r = 0
        for (i in 0 until n) {
            val target = nums[i]
            while (l + 1 <= i && nums[l + 1] < target - k) l++
            while (r < n && nums[r] <= target + k) r++
            var max = r - l - 1
            max = minOf(max, numOperations + cnt.getOrDefault(target, 0))
            ans = maxOf(max, ans)
        }

        // 取中间
        var i = 0
        var j = 0
        while (i < n) {
            while (j < n && nums[j] <= 0L + nums[i] + k + k) j++
            val max = minOf(numOperations, j - i)
            ans = maxOf(ans, max)
            i++
        }
        return ans
    }
}