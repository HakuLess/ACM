package leetcode.contest.c484

class SolutionB {
    fun centeredSubarrays(nums: IntArray): Int {
        val n = nums.size
        var ans = 0
        for (l in 0 until n) {
            var sum = 0
            val seen = HashSet<Int>()
            for (r in l until n) {
                sum += nums[r]
                seen.add(nums[r])
                if (sum in seen) {
                    ans++
                }
            }
        }
        return ans
    }
}
