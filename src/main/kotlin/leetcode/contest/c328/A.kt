package leetcode.contest.c328

class SolutionA {
    fun differenceOfSum(nums: IntArray): Int {
        var ans = 0
        nums.forEach {
            ans += it
            ans -= it.toString().sumBy { it - '0' }
        }
        return ans
    }
}