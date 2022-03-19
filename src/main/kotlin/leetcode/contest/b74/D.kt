package leetcode.contest.b74

import utils.print

fun main() {
    val s = SolutionD()
    s.minimumWhiteTiles("10110101", 2, 2).print()
    s.minimumWhiteTiles("11111", 2, 3).print()
}

class SolutionD {
    fun minimumWhiteTiles(floor: String, numCarpets: Int, carpetLen: Int): Int {
        val seen = HashMap<String, Int>()
        fun dfs(index: Int, left: Int): Int {
            val key = "$index,$left"
            if (key in seen) {
                return seen[key]!!
            }
            if (index !in floor.indices) {
                return 0
            }
            var ans: Int
            if (floor[index] == '1') {
                ans = dfs(index + 1, left) + 1
                if (left > 0) {
                    ans = minOf(ans, dfs(index + carpetLen, left - 1))
                }
            } else {
                ans = dfs(index + 1, left)
            }
            return ans.also {
                seen[key] = it
            }
        }
        return dfs(0, numCarpets)
    }
}