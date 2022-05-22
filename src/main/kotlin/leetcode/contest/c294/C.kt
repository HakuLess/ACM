package leetcode.contest.c294

import utils.gcd
import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = SolutionC()
    s.minimumLines("[[1,1],[500000000,499999999],[1000000000,999999998]]".toGrid()).print()
}

class SolutionC {
    fun minimumLines(stockPrices: Array<IntArray>): Int {
        stockPrices.sortBy { it[0] }
        val st = Stack<Pair<Int, Int>>()
        for (i in 1 until stockPrices.size) {
            val d = stockPrices[i][0] - stockPrices[i - 1][0]
            val p = stockPrices[i][1] - stockPrices[i - 1][1]
            val gcd = gcd(p, d)
            val next = Pair(d / gcd, p / gcd)
            if (st.isEmpty()) {
                st.push(next)
            } else if (st.peek() != next) {
                st.push(next)
            }
        }
        return st.size
    }
}