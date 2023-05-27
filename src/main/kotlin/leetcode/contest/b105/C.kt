package leetcode.contest.b105

class SolutionC {
    fun maxStrength(nums: IntArray): Long {
        if (nums.all { it == 0 }) return 0L
        if (nums.count { it > 0 } == 0 && nums.count { it < 0 } == 1) {
            if (nums.any { it == 0 }) return 0
//            else return nums.max()!!
            else return nums.maxOrNull()!!.toLong()
        }
        var ans = 1L
        nums.sort()
        for (i in nums.indices) {
            if (nums[i] > 0) {
                ans *= nums[i]
            }
        }
        for (i in nums.indices step 2) {
            if (i + 1 in nums.indices && nums[i + 1] < 0) {
                ans *= nums[i]
                ans *= nums[i + 1]
            }
        }
        return ans
    }
}