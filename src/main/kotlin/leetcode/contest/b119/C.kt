package leetcode.contest.b119

class SolutionC {
    fun maxSubarrayLength(nums: IntArray, k: Int): Int {
        var ans = 0
        val map = HashMap<Int, Int>()
        var left = 0

        for (right in nums.indices) {
            val item = nums[right]
            map[item] = map.getOrDefault(item, 0) + 1
            while (map[item]!! > k) {
                val l = nums[left]
                map[l] = map.getOrDefault(l, 0) - 1
                left++
            }
            ans = maxOf(ans, right - left + 1)
        }
        return ans
    }
}