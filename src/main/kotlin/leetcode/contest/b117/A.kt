package leetcode.contest.b117

import utils.print

fun main() {
    val s = SolutionA()
    s.distributeCandies(5, 2).print()
}

class SolutionA {
    fun distributeCandies(n: Int, limit: Int): Int {

        val seen = HashMap<String, Int>()
        fun dfs(index: Int, left: Int): Int {
            val key = "$index,$left"
            if (key in seen) {
                return seen[key]!!
            }
            if (left == 0) return 1
            if (index >= 3) return 0
            var tmp = 0
            for (i in 0..minOf(limit, left)) {
                tmp += dfs(index + 1, left - i)
            }
            return tmp.also {
                println("$key with $it")
                seen[key] = it
            }
        }
        return dfs(0, n)
    }
}