package leetcode.contest.c302

class SolutionA {
    fun numberOfPairs(nums: IntArray): IntArray {
        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            map[nums[i]] = map.getOrDefault(nums[i], 0) + 1
        }
        var a = 0
        var b = nums.size
        map.forEach { t, u ->
            a += u / 2
            b -= u / 2 * 2
        }
        return intArrayOf(a, b)
    }
}