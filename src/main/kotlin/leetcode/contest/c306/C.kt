package leetcode.contest.c306

import utils.permute
import utils.print

fun main() {
    val s = SolutionC()
//    s.smallestNumber("IIIDIDDD").print()
//    s.smallestNumber("IIIIIIDD").print()
    s.smallestNumber("DDD").print()
}

class SolutionC {
    fun smallestNumber(pattern: String): String {
        val n = pattern.length

        var first = if (pattern[0] == 'I') {
            -1
        } else {
            1
        }
        for (i in pattern.indices) {
            if (pattern[i] == 'D') {
                if (first >= 0) first++
            } else {
                break
            }
        }
        if (first == -1) first = 1

        val cur = ArrayList<Int>()
        for (i in 1..n + 1) {
            if (first != i)
                cur.add(i)
        }
        cur.toIntArray().permute().also {
            it.print()
        }.map {
            it.joinToString("").padStart(n + 1, '0' + first)
        }.forEach {
            var check = true
            for (i in 1..n) {
                if (pattern[i - 1] == 'I') {
                    if (it[i - 1] >= it[i]) {
                        check = false
                    }
                } else {
                    if (it[i - 1] <= it[i]) {
                        check = false
                    }
                }
            }
            if (check) return it
        }
        return ""
    }
}