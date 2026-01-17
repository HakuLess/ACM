package leetcode.contest.b174

import utils.print

fun main() {
    val s = SolutionC()
    // 1
    s.alternatingXOR(intArrayOf(2, 3, 1, 4), 1, 5).print()
    // 3
    s.alternatingXOR(intArrayOf(1, 0, 0), 1, 0).print()
    // 0
    s.alternatingXOR(intArrayOf(7), 1, 7).print()
}

class SolutionC {
    fun alternatingXOR(nums: IntArray, target1: Int, target2: Int): Int {
        val mod = 1_000_000_007L

        val n = nums.size
        val px = LongArray(n + 1)
        for (i in 0 until n) {
            px[i + 1] = px[i] xor nums[i].toLong()
        }

        val dp1 = LongArray(n + 1)
        val dp2 = LongArray(n + 1)
        dp1[0] = 1L

        val map1 = HashMap<Long, Long>()
        val map2 = HashMap<Long, Long>()
        map1[0] = 1L

        for (i in 1..n) {
            val need1 = px[i] xor target1.toLong()
            val need2 = px[i] xor target2.toLong()

            dp2[i] = map1.getOrDefault(need1, 0L)
            dp1[i] = map2.getOrDefault(need2, 0L)

            map1[px[i]] = (map1.getOrDefault(px[i], 0L) + dp1[i]) % mod
            map2[px[i]] = (map2.getOrDefault(px[i], 0L) + dp2[i]) % mod
        }

        return ((dp1[n] + dp2[n]) % mod).toInt()
    }
}