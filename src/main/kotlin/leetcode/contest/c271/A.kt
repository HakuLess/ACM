package leetcode.contest.c271

import utils.print

fun main() {
    val s = SolutionA()
    s.countPoints("B0B6G0R6R0R6G9").print()
}

class SolutionA {
    fun countPoints(rings: String): Int {
        val map = HashMap<Char, HashSet<Char>>()
        var i = 0
        while (i in rings.indices) {
            val index = rings[i + 1]
            val c = rings[i]
            i += 2
            map[index] = map.getOrDefault(index, hashSetOf())
            map[index]!!.add(c)
        }
        map.forEach { t, u ->
            println("$t, $u")
        }
        return map.filterKeys { map[it]!!.size == 3 }.count()
    }
}