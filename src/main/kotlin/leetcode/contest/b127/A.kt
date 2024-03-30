package leetcode.contest.b127

class SolutionA {
    fun minimumSubarrayLength(nums: IntArray, k: Int): Int {
        var ans = Int.MAX_VALUE
        for (i in nums.indices) {
            var cur = nums[i]
            for (j in i until nums.size) {
                cur = cur or nums[j]
                if (cur >= k) {
                    ans = minOf(ans, j - i + 1)
                    break
                }
            }
        }
        return if (ans == Int.MAX_VALUE) -1
        else ans
    }
}