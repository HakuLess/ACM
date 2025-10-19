package leetcode.contest.c472

// Not Finished
// 先暴力写一版 TLE 的
class SolutionD {
    fun longestBalanced(nums: IntArray): Int {
        var ans = 0
        for (i in nums.indices) {
            val a = mutableSetOf<Int>()
            val b = mutableSetOf<Int>()
            for (j in i until nums.size) {
                if (nums[j] % 2 == 0) {
                    a.add(nums[j])
                } else {
                    b.add(nums[j])
                }

                if (a.size == b.size) {
                    ans = maxOf(ans, j - i + 1)
                }
            }
        }
        return ans
    }
}