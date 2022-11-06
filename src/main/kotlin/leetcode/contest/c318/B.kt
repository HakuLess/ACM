package leetcode.contest.c318

class SolutionB {
    fun maximumSubarraySum(nums: IntArray, k: Int): Long {
        val map = HashMap<Int, Int>()
        var sum = 0L
        var ans = 0L
        for (i in nums.indices) {
            if (i >= k) {
                map[nums[i - k]] = map[nums[i - k]]!! - 1
                if (map[nums[i - k]] == 0) {
                    map.remove(nums[i - k])
                }
                sum -= nums[i - k]
            }
            sum += nums[i]
            map[nums[i]] = map.getOrDefault(nums[i], 0) + 1
            if (map.keys.size == k) {
                ans = maxOf(sum, ans)
            }
        }
        return ans
    }
}