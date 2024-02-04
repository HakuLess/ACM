package leetcode.contest.c383

class SolutionA {
    fun returnToBoundaryCount(nums: IntArray): Int {
        var ans = 0
        var cur = 0
        nums.forEach {
            cur += it
            if (cur == 0) ans++
        }
        return ans
    }
}