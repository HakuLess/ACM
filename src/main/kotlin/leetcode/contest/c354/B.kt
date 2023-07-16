package leetcode.contest.c354

class SolutionB {
    fun maximumBeauty(nums: IntArray, k: Int): Int {
        val c = IntArray(300000)
        for (i in nums.indices) {
            val l = maxOf(nums[i] - k, 0)
            val r = nums[i] + k + 1
            c[l]++
            c[r]--
        }
        var ans = 0
        var cur = 0
        for (i in c.indices) {
            cur += c[i]
            ans = maxOf(ans, cur)
        }
        return ans
    }
}