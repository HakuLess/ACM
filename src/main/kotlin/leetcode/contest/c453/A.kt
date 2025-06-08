package leetcode.contest.c453

import utils.print

fun main() {
    val s = Solution453()
//    s.canMakeEqual(intArrayOf(1, -1, 1), 2).print()
    // false
    s.canMakeEqual(intArrayOf(1, -1, 1, 1, -1, -1, -1, 1, -1), 4).print()
}

class Solution453 {
    fun canMakeEqual(nums: IntArray, k: Int): Boolean {

        fun tryTo(aar: IntArray, target: Int): Boolean {
            var cur = 0
            for (i in 0 until aar.lastIndex) {
                if (aar[i] != target && cur < k) {
//                    println("set $i & ${i + 1}")
                    aar[i] = -aar[i]
                    aar[i + 1] = -aar[i + 1]
                    cur++
                }
//                println("$cur : ${aar.joinToString()}")
            }
            return aar.all { it == target }.also {
//                println("target $target with $it")
            }
        }

        return tryTo(nums.clone(), 1) || tryTo(nums.clone(), -1)
    }
}