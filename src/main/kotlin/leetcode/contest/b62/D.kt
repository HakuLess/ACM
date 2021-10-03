package leetcode.contest.b62

import utils.print

fun main() {
    val s = Solution5874()
    s.waysToPartition(intArrayOf(2, -1, 2), 3).print()
//    s.waysToPartition(intArrayOf(22, 4, -25, -20, -15, 15, -16, 7, 19, -10, 0, -13, -14), -33).print()
}

class Solution5874 {
    fun waysToPartition(nums: IntArray, k: Int): Int {
        val leftMap = HashMap<Int, Int>()
        val sum = nums.sum()
        var pre = 0
        var ans = 0
        for (i in 0 until nums.lastIndex) {
            pre += nums[i]
            leftMap[pre] = leftMap.getOrDefault(pre, 0) + 1
            if (sum % 2 == 0 && pre == sum / 2) {
                ans++
            }
        }

        val rightMap = HashMap<Int, Int>()
        var suf = 0
        for (i in nums.indices.reversed()) {
            suf += nums[i]
            // 将nums[i]变为k
            val nSum = sum - nums[i] + k
            if (i != nums.lastIndex) {
                leftMap[pre] = leftMap.getOrDefault(pre, 0) - 1
                pre -= nums[i]
            }
            if (nSum % 2 == 0) {
                val right = rightMap.getOrDefault(nSum / 2, 0)
                val left = leftMap.getOrDefault(nSum / 2, 0)
                ans = maxOf(ans, right + left)
            }
            rightMap[suf] = rightMap.getOrDefault(suf, 0) + 1
        }
        return ans
    }
}