package leetcode.contest.c331

import utils.print
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = SolutionD()
    // 48
    s.minCost(
        intArrayOf(84, 80, 43, 8, 80, 88, 43, 14, 100, 88),
        intArrayOf(32, 32, 42, 68, 68, 100, 42, 84, 14, 8)
    ).print()
    // 8
    s.minCost(intArrayOf(4, 4, 4, 4, 3), intArrayOf(5, 5, 5, 5, 3)).print()
}

class SolutionD {
    fun minCost(basket1: IntArray, basket2: IntArray): Long {
        basket1.sort()
        basket2.sort()

        val map = HashMap<Int, Int>()
        val m = HashMap<Int, Int>()
        basket1.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
            m[it] = m.getOrDefault(it, 0) + 1
        }
        basket2.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        // 要求必须都为偶数
        if (map.any { it.value % 2 == 1 }) return -1L

        // 需要补充
        val a = ArrayList<Int>()
        // 需要抛弃
        val b = ArrayList<Int>()
        map.keys.sorted().forEach {
            val c = map[it]!! / 2 - m.getOrDefault(it, 0)
            if (c > 0) {
                repeat(c) { _ ->
                    a.add(it)
                }
            } else {
                repeat(-c) { _ ->
                    b.add(it)
                }
            }
        }
        var ans = 0L
//        val min = map.keys.min()!!
        val min = map.keys.minOrNull()!!
        a.sort()
        b.sortDescending()
        for (i in a.indices) {
            ans += minOf(a[i], b[i], 2 * min)
        }
        return ans
    }
}