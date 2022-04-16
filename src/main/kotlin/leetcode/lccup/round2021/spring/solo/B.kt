package leetcode.lccup.round2021.spring.solo

import utils.Bits
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionB()
    s.perfectMenu(
        intArrayOf(3, 2, 4, 1, 2),
        "[[1,1,0,1,2],[2,1,4,0,0],[3,2,4,1,0]]".toGrid(),
        "[[3,2],[2,4],[7,6]]".toGrid(),
        5
    ).print()
}

class SolutionB {
    fun perfectMenu(materials: IntArray, cookbooks: Array<IntArray>, attribute: Array<IntArray>, limit: Int): Int {
        val b = Bits(cookbooks.size)
        var ans = -1
        b.eachMask { mask ->
            val need = IntArray(materials.size)
            var x = 0
            var y = 0
            b.eachBit(mask) { index, b ->
                if (b) {
                    for (i in need.indices) {
                        need[i] += cookbooks[index][i]
                    }
                    x += attribute[index][0]
                    y += attribute[index][1]
                    if (y >= limit && (need.indices).all { need[it] <= materials[it] }) {
                        ans = maxOf(ans, x)
                    }
                }
            }
        }
        return ans
    }
}