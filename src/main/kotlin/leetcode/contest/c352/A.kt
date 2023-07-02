package leetcode.contest.c352

class SolutionA {
    fun longestAlternatingSubarray(nums: IntArray, threshold: Int): Int {
        if (nums.none { it <= threshold && it % 2 == 0 }) return 0
        var ans = 1
        for (l in nums.indices) {
            if (nums[l] % 2 != 0 || nums[l] > threshold) continue
            var r = l + 1
            while (r in nums.indices) {
                if (nums[r] > threshold || nums[r] % 2 == nums[r - 1] % 2) break
                r++
            }
            ans = maxOf(ans, r - l)
        }
        return ans
    }
}