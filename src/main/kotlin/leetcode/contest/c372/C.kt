package leetcode.contest.c372

import utils.print
import utils.toBigInteger

fun main() {
    val s = SolutionC()
//    s.maximumXorProduct(12, 5, 4).print()
//    s.maximumXorProduct(6, 7, 5).print()
//    s.maximumXorProduct(1, 6, 3).print()
//    s.maximumXorProduct(0, 4, 0).print()
    s.maximumXorProduct(53449611838892, 712958946092406, 6).print()
    s.maximumXorProduct(570713374625622, 553376364476768, 35).print()
}

class SolutionC {
    fun maximumXorProduct(a: Long, b: Long, n: Int): Int {
        val mod = 1000000007L
        var ans = a.toBigInteger() * b.toBigInteger()
        var tmpA = a
        var tmpB = b

        for (i in (0 until n).reversed()) {
            tmpA = tmpA xor (1L shl i)
            tmpB = tmpB xor (1L shl i)

            val cur = tmpA.toBigInteger() * tmpB.toBigInteger()

            if (cur > ans) {
                ans = cur
            } else {
                tmpA = tmpA xor (1L shl i)
                tmpB = tmpB xor (1L shl i)
            }
        }

        return (ans.mod(mod.toBigInteger())).toInt()
    }
}