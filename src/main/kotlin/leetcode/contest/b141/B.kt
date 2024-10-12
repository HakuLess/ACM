package leetcode.contest.b141

// Not Finished https://leetcode.cn/contest/biweekly-contest-141/problems/construct-the-minimum-bitwise-array-ii/
class SolutionB {
    fun minBitwiseArray(nums: List<Int>): IntArray {
        val n = nums.size
        val ans = IntArray(n) { -1 }

        for (i in 0 until n) {
            for (x in 0..nums[i]) {
                if ((x or (x + 1)) == nums[i]) {
                    ans[i] = x
                    break
                }
            }
        }
        return ans
    }
}