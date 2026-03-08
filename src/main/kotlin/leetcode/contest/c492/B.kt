package leetcode.contest.c492

class SolutionB {
    fun smallestBalancedIndex(nums: IntArray): Int {
        val n = nums.size

        val limit = 1_000_000_000_000_000L

        val suffix = LongArray(n + 1)
        suffix[n] = 1L

        for (i in n - 1 downTo 0) {
            val prod = suffix[i + 1] * nums[i].toLong()
            suffix[i] = if (prod > limit) limit else prod
        }

        var leftSum = 0L

        for (i in 0 until n) {
            val rightProduct = if (i + 1 < suffix.size) suffix[i + 1] else 1L
            if (leftSum == rightProduct) return i
            leftSum += nums[i]
        }

        return -1
    }
}