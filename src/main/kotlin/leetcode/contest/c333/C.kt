package leetcode.contest.c333

import utils.getPrime
import utils.print
import utils.quickPower
import java.util.HashSet

fun main() {
    val s = SolutionC()
//    s.squareFreeSubsets(intArrayOf(3, 4, 4, 5)).print()
//    s.squareFreeSubsets(intArrayOf(1, 1, 1)).print()
    // 3
//    s.squareFreeSubsets(intArrayOf(26, 6, 4, 27, 6, 18)).print()

    // 7
    s.squareFreeSubsets(intArrayOf(17, 27, 20, 1, 19)).print()
}

// 用GCD来计算相乘有没有平方数
class SolutionC {
    fun squareFreeSubsets(nums: IntArray): Int {
        val mod = 1000000007L
        val map = HashMap<Int, Long>()
        nums.forEach {
            map[it] = map.getOrDefault(it, 0) + 1L
        }
        val primes = ArrayList<IntArray>()
        primes.add(intArrayOf())
        val p = getPrime(32)
        for (it in 1..31) {
            var c = it
            val tmp = ArrayList<Int>()
            while (p[c] != 1) {
                tmp.add(p[c])
                c /= p[c]
            }
            tmp.add(c)
            primes.add(tmp.filter { it != 1 }.toIntArray())
        }

//        for (i in primes.indices) {
//            println("$i : ${primes[i].joinToString()}")
//        }

        val ans = ArrayList<ArrayList<Int>>()
        fun dfs(i: Int, set: HashSet<Int>, list: ArrayList<Int>) {
            if (i > 30) return
            dfs(
                i + 1,
                set.clone() as HashSet<Int>,
                list.clone() as ArrayList<Int> /* = java.util.ArrayList<kotlin.Int> */
            )

            if (primes[i].size == primes[i].toSet().size
                && primes[i].all { it !in set }
            ) {
                set.addAll(primes[i].toTypedArray())
                list.add(i)
                dfs(
                    i + 1,
                    set.clone() as HashSet<Int>,
                    list.clone() as ArrayList<Int> /* = java.util.ArrayList<kotlin.Int> */
                )
                ans.add(list)
            }
        }
        dfs(2, hashSetOf(), arrayListOf())
//        ans.size.print()
        var result = 0L
        ans.forEach {
            var tmp = 1L
            it.forEach {
                val c = map.getOrDefault(it, 0L)
                if (c == 0L) {
                    tmp = 0L
                    return@forEach
                } else {
                    tmp *= c
                }
                tmp %= mod
            }
            result += tmp
            result %= mod
        }
        val c1 = map.getOrDefault(1, 0L)
        if (c1 != 0L) {
            return (((result + 1) * (quickPower(2L, map.getOrDefault(1, 0L))) - 1L) % mod).toInt()
        }
        return result.toInt()
    }
}