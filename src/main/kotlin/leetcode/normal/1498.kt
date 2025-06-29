package leetcode.normal

import utils.print
import utils.quickPower

fun main() {
    val s = Solution1498()
    // 4
    s.numSubseq(intArrayOf(3, 5, 6, 7), 9).print()
    // 6
    s.numSubseq(intArrayOf(3, 3, 6, 8), 10).print()
    // 61
    s.numSubseq(intArrayOf(2, 3, 3, 4, 6, 7), 12).print()
}

class Solution1498 {
    fun numSubseq(nums: IntArray, target: Int): Int {
        val mod = 1_000_000_007L
        nums.sort()
        var right = nums.lastIndex
        var ans = 0L
        for (left in nums.indices) {
            if (nums[left] * 2 > target) break
            while (right - 1 >= left && nums[left] + nums[right] > target) {
                right--
            }
            // left..right-1 任意组合
            ans += quickPower(2L, (right - left).toLong(), mod).also {
//                println("ans add 2 pow ${right - left - 1} with $it")
            }
            ans %= mod
        }
        return ans.toInt()
    }
}