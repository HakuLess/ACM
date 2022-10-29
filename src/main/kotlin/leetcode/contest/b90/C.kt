package leetcode.contest.b90

import utils.print

fun main() {
    val s = SolutionC()
    s.destroyTargets(intArrayOf(1, 5, 3, 2, 2), 10000).print()
}

class SolutionC {
    fun destroyTargets(nums: IntArray, space: Int): Int {
        val map = HashMap<Int, Int>()
        nums.forEach {
            map[it % space] = map.getOrDefault(it % space, 0) + 1
        }
        var ans = Int.MAX_VALUE
        var cur = 0
        nums.forEach {
            if (map[it % space]!! > cur) {
                ans = it
                cur = map[it % space]!!
            } else if (map[it % space]!! == cur) {
                ans = minOf(ans, it)
                cur = map[it % space]!!
            }
        }
        return ans
    }
}