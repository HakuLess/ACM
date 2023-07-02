package leetcode.contest.c351

class SolutionC {
    fun numberOfGoodSubarraySplits(nums: IntArray): Int {
        val mod = 1000000007L
        val l = ArrayList<Int>()
        for (i in nums.indices) {
            if (nums[i] == 1) {
                l.add(i)
            }
        }
        if (l.isEmpty()) return 0
        if (l.size == 1) return 1
        var ans = 1L
        for (i in l.indices) {
            if (i == 0) continue
            ans *= (l[i] - l[i - 1])
            ans %= mod
        }
        return ans.toInt()
    }
}