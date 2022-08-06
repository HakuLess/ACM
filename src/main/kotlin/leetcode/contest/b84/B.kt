package leetcode.contest.b84

class SolutionB {
    fun countBadPairs(nums: IntArray): Long {
        val map = HashMap<Int, Int>()
        var ans = 0L
        for (i in nums.indices) {
            ans += i
            ans -= map.getOrDefault(nums[i] - i, 0)
            map[nums[i] - i] = map.getOrDefault(nums[i] - i, 0) + 1
        }
        return ans
    }
}