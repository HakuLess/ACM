package leetcode.contest.c300

import utils.print
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionC()
    s.peopleAwareOfSecret(6, 2, 4).print()
    s.peopleAwareOfSecret(4, 1, 3).print()
}

class SolutionC {
    fun peopleAwareOfSecret(n: Int, delay: Int, forget: Int): Int {
        var cur = ArrayList<Pair<Int, Long>>()
        val mod = 1000000007L
        cur.add(Pair(1, 1L))
        for (i in 1 until n) {
            val next = ArrayList<Pair<Int, Long>>()
            var tmp = 0L
            for (j in cur.indices) {
                if (cur[j].first >= forget) continue
                else {
                    if (cur[j].first >= delay)
                        tmp += cur[j].second
                    next.add(Pair(cur[j].first + 1, cur[j].second))
                }
            }
            if (tmp != 0L)
                next.add(Pair(1, tmp % mod))
            cur = next
        }
        var ans = 0L
        cur.forEach {
            ans += it.second
            ans %= mod
        }
        return ans.toInt()
    }
}