package leetcode.lccup.a

import utils.print
import kotlin.collections.HashMap

fun main() {
    val s = SolutionB()
    s.maxmiumScore(intArrayOf(1, 2, 8, 9), 3).print()
    s.maxmiumScore(intArrayOf(3, 3, 1), 1).print()
}

class SolutionB {
    fun maxmiumScore(cards: IntArray, cnt: Int): Int {
        val st = cards.sortedDescending()
        val mapA = HashMap<Int, Int>()
        mapA[0] = 0
        val mapB = HashMap<Int, Int>()
        mapB[0] = 0
        var a = 0
        var ca = 0
        var b = 0
        var cb = 0
        st.forEach {
            if (it % 2 == 0) {
                a += it
                ca++
                mapA[ca] = a
            } else {
                b += it
                cb++
                if (cb % 2 == 0) {
                    mapB[cb] = b
                }
            }
        }
        var ans = 0
        for (i in 0..cnt) {
            ans = maxOf(ans, mapA.getOrDefault(i, Int.MIN_VALUE / 3) + mapB.getOrDefault(cnt - i, Int.MIN_VALUE / 3))
        }
        return ans
    }
}