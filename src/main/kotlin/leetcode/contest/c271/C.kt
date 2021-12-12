package leetcode.contest.c271

import utils.print

fun main() {
    val s = SolutionC()
//    s.minimumRefill(intArrayOf(2, 2, 3, 3), 5, 5).print()
    s.minimumRefill(intArrayOf(2, 2, 3, 3), 3, 4).print()
//    s.minimumRefill(intArrayOf(274,179,789,417,293,336,133,334,569,355,813,217,80,933,961,271,294,933,49,980,685,470,186,11,157,889,299,493,215,807,588,464,218,248,391,817,32,606,740,941,505,533,289,306,490), 996, 1172).print()
}

class SolutionC {
    fun minimumRefill(plants: IntArray, capacityA: Int, capacityB: Int): Int {
        var l = 0
        var r = plants.lastIndex
        var a = capacityA
        var b = capacityB
        var c = 0
        while (l <= r) {
            println("$l:$a $r:$b")
            if (l == r) break
            if (l in plants.indices && a >= plants[l]) {
                a -= plants[l]
                l++
            } else {
                a = capacityA
                c++
                a -= plants[l]
                l++
            }
            if (r in plants.indices && b >= plants[r]) {
                b -= plants[r]
                r--
            } else {
                b = capacityB
                c++
                b -= plants[r]
                r--
            }
        }
        if (l == r) {
            if (b >= plants[l] || a >= plants[l]) {

            } else {
                c++
            }
        }
        return c
    }
}