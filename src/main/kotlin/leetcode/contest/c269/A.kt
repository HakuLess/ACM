package leetcode.contest.c269

class SolutionA {
    fun targetIndices(nums: IntArray, target: Int): List<Int> {
        val ans = ArrayList<Int>()
        nums.sorted().forEachIndexed { index, i ->
            if (i == target) {
                ans.add(index)
            }
        }
        return ans
    }
}