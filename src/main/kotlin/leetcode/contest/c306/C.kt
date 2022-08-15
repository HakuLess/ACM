package leetcode.contest.c306

import utils.nextPermutation
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
        val cur = Array<Int>(n + 1) { 0 }
        for (i in 1..n + 1) {
            cur[i - 1] = i
        }
        do {
            cur.joinToString("").let {
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
//                println("$check $it")
                if (check) return it
            }
        } while (cur.nextPermutation())
        return ""
    }
}