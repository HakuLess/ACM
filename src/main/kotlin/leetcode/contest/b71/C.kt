package leetcode.contest.b71

import utils.print

fun main() {
    val s = SolutionC()
//    s.minCostSetTime(1, 2, 1, 600).print()
    s.minCostSetTime(1, 475, 722, 6016).print()
}

class SolutionC {
    fun minCostSetTime(startAt: Int, moveCost: Int, pushCost: Int, targetSeconds: Int): Int {
        var a = targetSeconds
        var m = 0
        var s = 0
        while (a >= 100) {
            a -= 60
            m++
        }
        s = targetSeconds - m * 60
        fun dfs(m: Int, s: Int): Int {
            var ans = 0
            var cur = startAt
            val target = m.toString().padStart(2, '0') + s.toString().padStart(2, '0')
            if (target.all { it == '0' }) return 0
            val nz = target.indexOfFirst { it != '0' }
            for (i in nz..3) {
                if (target[i] - '0' != cur) {
                    ans += moveCost
                    ans += pushCost
                    cur = target[i] - '0'
                } else {
                    ans += pushCost
                }
            }
            return ans
        }

        var ans = dfs(m, s)
        if (s >= 60 && m + 1 <= 99) {
            ans = minOf(ans, dfs(m + 1, s - 60))
        }
        return ans
    }
}