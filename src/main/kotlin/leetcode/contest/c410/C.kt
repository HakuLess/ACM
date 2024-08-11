package leetcode.contest.c410

import utils.print

fun main() {
    val s = SolutionC()
    s.countOfPairs(intArrayOf(2, 3, 2)).print()
    s.countOfPairs(intArrayOf(5, 5, 5, 5)).print()
    val arr = ArrayList<Int>()
    repeat(280) {
        arr.add(1000)
    }
    s.countOfPairs(arr.toIntArray()).print()
}

class SolutionC {
    fun countOfPairs(nums: IntArray): Int {
        val mod = 1000000007L
        val seen = HashMap<Long, Long>()
        fun dfs(preA: Int, pos: Int): Long {
            if (pos !in nums.indices) return 1
            val key = 0L + pos + preA * 2001
            val preB = if (pos == 0) Int.MAX_VALUE else nums[pos - 1] - preA
            if (key in seen) return seen[key]!!
            var tmp = 0L
            val start = maxOf(preA, nums[pos] - preB, 0)
            for (cur in start..nums[pos]) {
                val next = dfs(cur, pos + 1)
                tmp += next
                tmp %= mod
            }
            return tmp.also {
                seen[key] = it
            }
        }

        val ans = dfs(-1, 0)
        return (ans % mod).toInt()
    }
}

//1 0 2 with 3
//1 1 1 with 1
//1 2 0 with 0
//0 -1 2147483647 with 10
//1 -1 3 with 6
//2 0 3 with 3
//2 1 2 with 2
//2 2 1 with 1
//2 3 0 with 0
//Int is 10

//1 0 2 with 3
//1 1 1 with 1
//1 2 0 with 0
//0 -1 2147483647 with 4
//2 1 2 with 2
//2 2 1 with 1
//2 3 0 with 0
//Int is 4