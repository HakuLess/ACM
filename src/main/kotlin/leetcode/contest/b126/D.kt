package leetcode.contest.b126

import utils.print
import utils.quickPower
import utils.toBigInteger
import java.math.BigInteger

fun main() {
    val s = SolutionD()
//    s.sumOfPower(intArrayOf(1, 2, 3), 3).print()
//    s.sumOfPower(intArrayOf(2, 3, 3), 5).print()
    // 32
    s.sumOfPower(intArrayOf(1, 2, 1, 5, 4), 5).print()
}

class SolutionD {

    fun sumOfPower(nums: IntArray, k: Int): Int {
        val mod = 1000000007L
        val n = nums.size

        val map = HashMap<Pair<Int, Int>, Long>()
        for (i in nums.indices) {
            val toAdd = ArrayList<Triple<Int, Int, Long>>()
            map.keys.forEach {
                if (it.second + nums[i] <= k) {
                    toAdd.add(Triple(it.first + 1, it.second + nums[i], map[it]!!))
                }
            }
            toAdd.add(Triple(1, nums[i], 1L))

            toAdd.forEach {
                map[Pair(it.first, it.second)] = (map.getOrDefault(Pair(it.first, it.second), 0L) + it.third) % mod
            }
        }

        var ans = BigInteger.ZERO

        map.keys.forEach {
            if (it.second == k) {
                val need = it.first
                ans += quickPower(BigInteger.valueOf(2L), (n - need).toBigInteger(), mod) * map[it]!!.toBigInteger()
                ans = ans.mod(mod.toBigInteger())
            }
        }
        return (ans.toLong() % mod).toInt()
    }
}