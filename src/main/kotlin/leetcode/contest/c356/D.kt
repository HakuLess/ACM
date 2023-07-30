package leetcode.contest.c356

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionD()
    s.countSteppingNumbers("1", "11").print()
    s.countSteppingNumbers("90", "101").print()

    // 18
    s.countSteppingNumbers("17", "149").print()
}

class SolutionD {
    fun countSteppingNumbers(low: String, high: String): Int {

        val mod = 1000000007L
        val seen = HashMap<String, Long>()

        fun dfs(cur: String, target: String): Long {
            val key = if (target.startsWith(cur)) {
                "${target}_start_${cur}"
            } else if (cur > target.substring(0, cur.length)) {
                "${target}_len_more_${cur.length}_${cur.last()}"
            } else {
                "${target}_len_less_${cur.length}_${cur.last()}"
            }
            if (key in seen) return seen[key]!!
            if (cur.length > target.length) return 0
            else if (cur.length == target.length) {
                if (cur <= target) return 1
                else return 0
            }

            var tmp = 1L
            val lst = cur.last() - '0'
            if (lst + 1 in 0..9) {
                tmp += dfs("$cur${lst + 1}", target)
                tmp %= mod
            }
            if (lst - 1 in 0..9) {
                tmp += dfs("$cur${lst - 1}", target)
                tmp %= mod
            }
            return tmp.also {
                seen[key] = it
            }
        }

        var ans = 0L
        for (i in 1..9) {
            ans += dfs(i.toString(), high)
            ans %= mod
        }
        for (i in 1..9) {
            ans -= dfs(i.toString(), low)
            ans %= mod
        }

        if (low.indices.all {
                if (it == 0) true
                else abs(low[it] - low[it - 1]) == 1
            }) {
            ans++
        }

        ans %= mod
        while (ans < 0) {
            ans += mod
        }
//        seen.forEach { t, u ->
//            println("$t with $u")
//        }
        return ans.toInt()
    }
}