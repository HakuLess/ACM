package leetcode.contest.c337

import utils.print

fun main() {
    val s = SolutionD()
    s.findSmallestInteger(intArrayOf(1, -10, 7, 13, 6, 8), 5).print()
}

class SolutionD {
    fun findSmallestInteger(nums: IntArray, value: Int): Int {
        val map = HashMap<Int, Int>()
        nums.forEach {
            val key = (it % value + value) % value
            map[key] = map.getOrDefault(key, 0) + 1
        }
        var cur = 0
        while (cur % value in map.keys) {
            val v = cur % value
            map[v] = map[v]!! - 1
            if (map[v] == 0) {
                map.remove(v)
            }
            cur++
        }
        return cur
    }
}