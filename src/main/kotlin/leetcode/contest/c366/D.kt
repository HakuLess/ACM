package leetcode.contest.c366

import utils.print
import utils.printInt

fun main() {
    val s = SolutionD()
//    s.maxSum(listOf(2, 6, 5, 8), 2).print()
//    s.maxSum(listOf(4, 5, 4, 7), 3).print()
    s.maxSum(listOf(32, 85, 61), 1).print()

    125.toString(2).print()
}

class SolutionD {
    fun maxSum(nums: List<Int>, k: Int): Int {
        val map = HashMap<Int, Int>()
        for (i in 0 until 32) {
            map[i] = 0
        }
        for (i in nums.indices) {
            var index = 0
            nums[i].toString(2).reversed().forEach {
                if (it == '1')
                    map[index] = map.getOrDefault(index, 0) + 1
                index++
            }
        }
        map.printInt()
        val arr = LongArray(k)
        var step = 1L
        map.keys.sorted().forEach {
            for (i in 0 until minOf(k, map[it]!!)) {
                arr[i] += step
            }
            step *= 2
        }

        val mod = 1000000007L
        var ans = 0L
        for (i in arr.indices) {
            ans += arr[i] * arr[i]
            ans %= mod
        }
        return (ans % mod).toInt()
    }
}