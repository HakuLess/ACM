package leetcode.normal

import utils.print
import java.util.TreeSet

fun main() {
    val s = Solution1488()
    s.avoidFlood(intArrayOf(1, 2, 0, 0, 2, 1)).print()
    s.avoidFlood(intArrayOf(69, 0, 0, 0, 69)).print()
}

class Solution1488 {
    fun avoidFlood(rains: IntArray): IntArray {
        val n = rains.size
        val ans = IntArray(n) { 1 }
        val lastRain = HashMap<Int, Int>()
        val dryDays = TreeSet<Int>()

        for (i in rains.indices) {
            val lake = rains[i]
            if (lake == 0) {
                dryDays.add(i)
            } else {
                ans[i] = -1
                if (lake in lastRain.keys) {
                    val dryDay = dryDays.higher(lastRain[lake]) ?: return intArrayOf()
                    ans[dryDay] = lake
                    dryDays.remove(dryDay)
                }
                lastRain[lake] = i
            }
        }

        return ans
    }
}