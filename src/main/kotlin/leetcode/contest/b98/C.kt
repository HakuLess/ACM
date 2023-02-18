package leetcode.contest.b98

class SolutionC {
    fun minImpossibleOR(nums: IntArray): Int {
        nums.sort()
        var cur = 1
        while (nums.binarySearch(cur) >= 0) {
            cur *= 2
        }
        return cur
    }
}