package leetcode.contest.c352

import utils.MultiSet

class SolutionC {
    fun continuousSubarrays(nums: IntArray): Long {
        val st = MultiSet<Int>()
        var l = 0
        var r = 0
        var ans = 0L
        while (r in nums.indices) {
            st.add(nums[r])
            while (st.max - st.min > 2) {
                st.remove(nums[l])
                l++
            }
            ans += 1L + r - l
            r++
        }
        return ans
    }
}