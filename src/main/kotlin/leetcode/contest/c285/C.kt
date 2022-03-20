package leetcode.contest.c285

import utils.Bits
import utils.print

fun main() {
    val s = SolutionC()
    s.maximumBobPoints(9, intArrayOf(1, 1, 0, 1, 0, 0, 2, 1, 0, 1, 2, 0)).print()
    s.maximumBobPoints(3, intArrayOf(0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2)).print()
}

class SolutionC {
    fun maximumBobPoints(numArrows: Int, aliceArrows: IntArray): IntArray {
        val b = Bits(12)
        var ans = 0
        var result = intArrayOf()
        b.eachMask { mask ->
            var need = 0
            var tmp = 0
            val res = IntArray(12)
            b.eachBit(mask) { index, b ->
                if (b) {
                    need += aliceArrows[index] + 1
                    res[index] = aliceArrows[index] + 1
                    tmp += index
                }
            }
            if (numArrows >= need && tmp >= ans) {
                ans = maxOf(ans, tmp)
                result = res
            }
        }
        result[0] += numArrows - result.sum()
        return result
    }
}