package leetcode.contest.c403

class SolutionA {
    fun minimumAverage(nums: IntArray): Double {
        nums.sort()
        val n = nums.size
        val avgs = ArrayList<Double>()
        for (i in 0 until n / 2) {
            avgs.add((0.0 + nums[i] + nums[n - i - 1]) / 2)
        }
        return avgs.minOrNull()!!
    }
}