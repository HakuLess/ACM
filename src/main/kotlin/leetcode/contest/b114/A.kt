package leetcode.contest.b114

class SolutionA {
    fun minOperations(nums: List<Int>, k: Int): Int {
        val set = HashSet<Int>()
        var ans = 0
        for (i in nums.indices.reversed()) {
            ans++
            if (nums[i] <= k) {
                set.add(nums[i])
            }
            if (set.size == k) return ans
        }
        return ans
    }
}