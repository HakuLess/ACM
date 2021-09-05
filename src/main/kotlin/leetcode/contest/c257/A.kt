package leetcode.contest.c257

class Solution5863 {
    fun countQuadruplets(nums: IntArray): Int {
        var ans = 0
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                for (k in j + 1 until nums.size) {
                    for (l in k + 1 until nums.size) {
                        if (nums[i] + nums[j] + nums[k] == nums[l])
                            ans++
                    }
                }
            }
        }
        return ans
    }
}