package leetcode.contest.c325

import utils.biMax
import utils.print

fun main() {
    val s = SolutionC()
    s.maximumTastiness(intArrayOf(13, 5, 1, 8, 21, 2), 3).print()
}

class SolutionC {
    fun maximumTastiness(price: IntArray, k: Int): Int {
        price.sort()
        return biMax(0L, Long.MAX_VALUE / 2) {
            var cur = price[0]
            var ans = 1
            for (i in price.indices) {
                if (price[i] >= cur + it) {
                    ans++
                    cur = price[i]
                }
            }
            ans >= k
        }.toInt()
    }
}