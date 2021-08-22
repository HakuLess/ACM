package leetcode.normal

import kotlin.math.abs

class Solution789 {
    fun escapeGhosts(ghosts: Array<IntArray>, target: IntArray): Boolean {
        var min = Int.MAX_VALUE
        ghosts.forEach {
            val cur = abs(it[0] - target[0]) + abs(it[1] - target[1])
            min = minOf(min, cur)
        }
        return abs(target[0]) + abs(target[1]) < min
    }
}