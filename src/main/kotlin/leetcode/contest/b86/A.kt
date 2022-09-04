package leetcode.contest.b86

class SolutionA {
    fun findSubarrays(nums: IntArray): Boolean {
        val set = hashSetOf<Int>()
        for (i in 0 until nums.lastIndex) {
            val c = nums[i] + nums[i + 1]
            if (c in set) return true
            set.add(c)
        }
        return false
    }
}