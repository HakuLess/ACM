package leetcode.contest.c308

import utils.preSumArray
import utils.print

fun main() {
    val s = SolutionC()
//    s.garbageCollection(arrayOf("G", "P", "GP", "GG"), intArrayOf(2, 4, 3)).print()
    s.garbageCollection(arrayOf("G", "M"), intArrayOf(1)).print()
}

class SolutionC {
    fun garbageCollection(garbage: Array<String>, travel: IntArray): Int {
        var sum = 0
        var preSum = travel.preSumArray(false)

        arrayOf('G', 'P', 'M').forEach { c ->
            for (i in garbage.indices) {
                garbage[i].count { it == c }.let { cnt ->
                    if (cnt != 0) {
                        sum += cnt
                    }
                }
            }

            for (i in garbage.indices.reversed()) {
                if (garbage[i].contains(c)) {
                    if (i == 0) break
                    sum += preSum[i - 1].toInt()
                    break
                }
            }
        }
        return sum
    }
}