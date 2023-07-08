package leetcode.contest.b108

import utils.print

fun main() {
    val s = SolutionC()
    s.minimumBeautifulSubstrings("1011").print()
}

class SolutionC {
    fun minimumBeautifulSubstrings(s: String): Int {
//        var c = 1
//        repeat(10) {
//            println("${c.toString(2)} with ${c.toString(2).length}")
//            c *= 5
//        }
        val set = hashSetOf("1", "101", "11001", "1111101", "1001110001", "110000110101", "11110100001001")

        val seen = HashMap<String, Int>()

        fun dfs(s: String): Int {
            if (s in seen.keys) return seen[s]!!
            if (s in set) return 1

            var tmp = 20
            for (next in set) {
                if (s.startsWith(next)) {
                    val l = next.length
                    tmp = minOf(tmp, 1 + dfs(s.substring(l, s.length)))
                }
            }
            return tmp.also {
                seen[s] = it
            }
        }

        return dfs(s).let {
            if (it >= 20) -1
            else it
        }
    }
}