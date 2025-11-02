package leetcode.contest.c474

class SolutionA {
    fun findMissingElements(nums: IntArray): List<Int> {
        val min = nums.min()
        val max = nums.max()
        val set = nums.toSet()
        val ans = mutableListOf<Int>()
        for (x in min..max) {
            if (x !in set) ans.add(x)
        }
        return ans
    }
}