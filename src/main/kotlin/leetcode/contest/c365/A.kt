package leetcode.contest.c365

class SolutionA {
    fun maximumTripletValue(nums: IntArray): Long {
        var ans = 0L
        for (i in 0 until nums.size) {
            for (j in i + 1 until nums.size) {
                for (k in j + 1 until nums.size) {
                    ans = maxOf(ans, (nums[i].toLong() - nums[j]) * nums[k])
                }
            }
        }
        return ans
    }
}