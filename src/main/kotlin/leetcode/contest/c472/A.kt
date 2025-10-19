package leetcode.contest.c472

class SolutionA {
    fun missingMultiple(nums: IntArray, k: Int): Int {
        val set = nums.toHashSet()
        var cur = k
        while (true) {
            if (cur !in set) {
                return cur
            }
            cur += k
        }
    }
}