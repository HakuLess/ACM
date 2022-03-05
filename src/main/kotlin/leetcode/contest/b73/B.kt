package leetcode.contest.b73

class SolutionB {
    fun sortJumbled(mapping: IntArray, nums: IntArray): IntArray {
        val list = nums.sortedBy {
            var ans = 0
            it.toString().forEach {
                ans *= 10
                ans += mapping[(it - '0')]
            }
            ans
        }
        return list.toIntArray()
    }
}