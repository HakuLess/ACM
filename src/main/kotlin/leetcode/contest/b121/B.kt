package leetcode.contest.b121

class SolutionB {
    fun minOperations(nums: IntArray, k: Int): Int {
        var c = 0
        nums.forEach {
            c = c xor it
        }
        c = c xor k
        return c.toString(2).count { it == '1' }
    }
}