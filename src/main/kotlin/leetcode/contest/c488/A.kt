package leetcode.contest.c488

class SolutionA {
    fun dominantIndices(nums: IntArray): Int {
        val n = nums.size
        if (n <= 1) return 0

        val suffix = IntArray(n)
        suffix[n - 1] = nums[n - 1]
        for (i in n - 2 downTo 0) {
            suffix[i] = suffix[i + 1] + nums[i]
        }

        var ans = 0
        for (i in 0 until n - 1) {
            val rightCount = n - i - 1
            val rightSum = suffix[i + 1]
            if (nums[i] * rightCount > rightSum) {
                ans++
            }
        }

        return ans
    }
}