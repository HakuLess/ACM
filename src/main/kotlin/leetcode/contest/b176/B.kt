package leetcode.contest.b176

class SolutionB {
    fun rob(nums: IntArray, colors: IntArray): Long {
        var take = nums[0].toLong()
        var skip = 0L

        for (i in 1 until nums.size) {
            val prevTake = take
            val prevSkip = skip
            skip = maxOf(prevTake, prevSkip)
            take = if (colors[i] == colors[i - 1]) {
                prevSkip + nums[i]
            } else {
                maxOf(prevTake, prevSkip) + nums[i]
            }
        }

        return maxOf(take, skip)
    }
}