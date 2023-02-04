package leetcode.contest.b97

import utils.print
import java.util.*


fun main() {
    val s = SolutionC()
    s.maximizeWin(intArrayOf(1, 1, 2, 2, 3, 3, 5), 2).print()
}

class SolutionC {
    fun maximizeWin(prizePositions: IntArray, k: Int): Int {
        val map = TreeMap<Int, Int>()
        var c = 0
        prizePositions.forEach {
            c++
            map[it] = c
        }
        var max = 0
        var prev = 0
        for (i in map.keys) {
            // 当前最大值
            max = maxOf(
                max,
                prev + map.floorEntry(i + k).value - if (map.firstKey() < i) map.lowerEntry(i).value else 0
            )
            // 之前最大值
            prev = maxOf(prev, map[i]!! - if (map.firstKey() < i - k) map.lowerEntry(i - k).value else 0)
        }
        return max
    }
}