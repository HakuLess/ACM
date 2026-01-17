package leetcode.contest.b174

import kotlin.math.abs

class SolutionA {
    fun bestTower(towers: Array<IntArray>, center: IntArray, radius: Int): IntArray {
        val cx = center[0]
        val cy = center[1]

        var bestQuality = -1
        var bestX = -1
        var bestY = -1

        for (t in towers) {
            val x = t[0]
            val y = t[1]
            val q = t[2]

            val dist = abs(x - cx) + abs(y - cy)
            if (dist > radius) continue

            if (q > bestQuality || (q == bestQuality && (x < bestX || (x == bestX && y < bestY)))) {
                bestQuality = q
                bestX = x
                bestY = y
            }
        }

        return if (bestQuality == -1) intArrayOf(-1, -1)
        else intArrayOf(bestX, bestY)
    }
}