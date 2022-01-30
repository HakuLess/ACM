package leetcode.contest.c278

class SolutionA {
    fun findFinalValue(nums: IntArray, original: Int): Int {
        var c = original
        while (c in nums) {
            c *= 2
        }
        return c
    }
}