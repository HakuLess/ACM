package leetcode.contest.c281

import utils.lcm
import utils.print

fun main() {
    val s = SolutionD()
    s.coutPairs(intArrayOf(1, 2, 3, 4, 5), 2).print()
    s.coutPairs(intArrayOf(1, 2, 3, 4), 5).print()
    s.coutPairs(intArrayOf(3, 2, 6, 1, 8, 4, 1), 3).print()
}

class SolutionD {
    fun coutPairs(nums: IntArray, k: Int): Long {
        val max = nums.maxOrNull()!!
//        val max = nums.max()!!
        val map = HashMap<Int, Int>()
        nums.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        val arr = IntArray(max + 1)
        for (i in 1..max) {
            var step = 1
            while (i * step <= max) {
                if (map.getOrDefault(i * step, 0) != 0) {
                    arr[i] += map[i * step]!!
                }
                step++
            }
        }

        var ans = 0L
        for (i in nums.indices) {
            map[nums[i]] = map.getOrDefault(nums[i], 0) - 1
            // 最小公倍数
            val tmp = lcm(nums[i].toLong(), k.toLong())
            val lst = (tmp / nums[i]).toInt()
            if (lst <= max) {
                ans += arr[lst]
                if (nums[i] % lst == 0) {
                    ans--
                }
            }
        }
        return ans / 2
    }
}