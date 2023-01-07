package leetcode.contest.b95

class SolutionC {
    fun xorBeauty(nums: IntArray): Int {
        var ans = 0
        nums.forEach {
            ans = ans xor it
        }
        return ans
    }
}