package leetcode.contest.c332

import utils.*

fun main() {
    val s = SolutionB()
    s.countFairPairs(intArrayOf(0, 1, 7, 4, 4, 5), 3, 6).print()
    s.countFairPairs(intArrayOf(-1, 0), 1, 1).print()

    s.countFairPairs(intArrayOf(7, 9, 8, 6, -1000000000, -1000000000, -1000000000, -1000000000), -14, 11).print()
}

class SolutionB {
    fun countFairPairs(nums: IntArray, lower: Int, upper: Int): Long {
        nums.sort()
        var ans = 0L
        val l = ArrayList<Int>()
        nums.forEach {
            val left = lower - it
            val right = upper - it
            val a = l.biFirstIndexOf { it >= left }
            val b = l.biLastIndexOf { it <= right }
            if (a == -1 || b == -1) {

            } else {
                println("$it query $left to $right with ${b - a + 1}")
                ans += b - a + 1
            }
            l.add(it)
        }

        return ans
    }
}