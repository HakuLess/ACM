package leetcode.contest.b122

class SolutionA {
    fun minimumCost(nums: IntArray): Int {
        val arr = ArrayList<Int>()
        for (i in 1 until nums.size) {
            arr.add(nums[i])
        }
        return nums[0] + arr.sorted().take(2).sum()
    }
}