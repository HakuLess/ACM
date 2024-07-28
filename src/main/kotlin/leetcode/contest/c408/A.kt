package leetcode.contest.c408

class SolutionA {
    fun canAliceWin(nums: IntArray): Boolean {
        var sumSingleDigit = 0
        var sumDoubleDigit = 0

        for (num in nums) {
            if (num < 10) {
                sumSingleDigit += num
            } else {
                sumDoubleDigit += num
            }
        }

        return sumSingleDigit != sumDoubleDigit
    }
}