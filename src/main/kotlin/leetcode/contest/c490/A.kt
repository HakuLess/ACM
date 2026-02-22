package leetcode.contest.c490

class SolutionA {
    fun scoreDifference(nums: IntArray): Int {
        var active = 0
        val score = IntArray(2)

        for (i in nums.indices) {
            if (nums[i] % 2 == 1) {
                active = active xor 1
            }
            if (i % 6 == 5) {
                active = active xor 1
            }
            score[active] += nums[i]
        }
        return score[0] - score[1]
    }
}