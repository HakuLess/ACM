package leetcode.contest.b111

class SolutionA {
    fun countPairs(nums: List<Int>, target: Int): Int {
        var ans = 0
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] < target) ans++
            }
        }
        return ans
    }
}