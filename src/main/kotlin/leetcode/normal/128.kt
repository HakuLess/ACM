package leetcode.normal


class Solution128 {
    fun longestConsecutive(nums: IntArray): Int {
        val set = nums.toHashSet()

        var ans = 0
        for (num in set) {
            if (!set.contains(num - 1)) {
                var cur = num
                var tmp = 1
                while (set.contains(cur + 1)) {
                    cur += 1
                    tmp += 1
                }
                ans = maxOf(ans, tmp)
            }
        }

        return ans
    }
}