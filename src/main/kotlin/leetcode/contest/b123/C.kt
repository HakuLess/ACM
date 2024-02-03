package leetcode.contest.b123

import utils.print

fun main() {
    val s = SolutionC()
    s.maximumSubarraySum(intArrayOf(1, 2, 3, 4, 5, 6), 1).print()
    s.maximumSubarraySum(intArrayOf(-1, 3, 2, 4, 5), 3).print()
    s.maximumSubarraySum(intArrayOf(-1, -2, -3, -4), 2).print()
}

class SolutionC {
    fun maximumSubarraySum(nums: IntArray, k: Int): Long {
        var sum = 0L
        var ans = Long.MIN_VALUE
        val minMap = HashMap<Int, Long>()
        for (i in nums.indices) {
            val key = nums[i]

            val lst = minMap.getOrDefault(key, Long.MAX_VALUE)
            minMap[key] = minOf(sum, lst)

            sum += key

            if (key - k in minMap.keys) {
//                println("find $key - $k with ${key - k}")
                ans = maxOf(ans, sum - minMap[key - k]!!)
            }
            if (key + k in minMap.keys) {
//                println("find $key + $k with ${key + k}")
                ans = maxOf(ans, sum - minMap[key + k]!!)
            }
        }
        return ans.let { if (it == Long.MIN_VALUE) 0 else it }
    }
}