package leetcode.contest.b135

import utils.print
import kotlin.collections.HashMap

fun main() {
    val s = SolutionB()
    s.minimumLength("abaacbcbb").print()
}

class SolutionB {
    fun minimumLength(s: String): Int {
        val map = HashMap<Char, Int>()
        s.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }

        var ans = 0
        map.values.forEach {
            var cur = it
            while (cur >= 3) {
                cur -= 2
            }
            ans += cur
        }
        return ans
    }
}