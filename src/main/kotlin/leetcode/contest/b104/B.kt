package leetcode.contest.b104

class SolutionB {
    fun matrixSum(nums: Array<IntArray>): Int {
        nums.forEach {
            it.sort()
        }
        var ans = 0
        for (i in nums[0].indices) {
            var c = 0
            for (j in nums.indices) {
                c = maxOf(c, nums[j][i])
            }
            ans += c
        }
        return ans
    }
}