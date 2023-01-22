package leetcode.contest.b96

import utils.print

fun main() {
    val s = SolutionD()
    s.isReachable(6, 9).print()
    s.isReachable(4, 7).print()
    s.isReachable(536870912, 536870912).print()
    s.isReachable(671088640, 939524096).print()
}

class SolutionD {
    fun isReachable(targetX: Int, targetY: Int): Boolean {
        val seen = HashMap<String, Boolean>()
        val set = HashSet<String>()
        fun dfs(a: Int, b: Int): Boolean {
            val x = minOf(a, b)
            val y = maxOf(a, b)
            if (x <= 0 || y <= 0) return false
            val key = "$x,$y"
            if (key in set) return false
            set.add(key)
            if (key in seen) return seen[key]!!

            if (x == 1 && y == 1) return true
            var tmp = false
            if (x % 2 == 0)
                tmp = tmp or dfs(x / 2, y)
            if (y % 2 == 0)
                tmp = tmp or dfs(x, y / 2)
            if (x % 2 != 0 && y % 2 != 0)
                tmp = tmp or dfs(x, y + x)

            return tmp.also {
                seen[key] = it
            }
        }

        return dfs(targetX, targetY)
    }
}