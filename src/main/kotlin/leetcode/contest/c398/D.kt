package leetcode.contest.c398

import utils.print
import utils.quickPower
import kotlin.collections.HashMap

fun main() {
    val s = SolutionD()
    // 2
    s.waysToReachStair(0).print()
    // 4
    s.waysToReachStair(1).print()
    // 4
    s.waysToReachStair(2).print()
    // 3
    s.waysToReachStair(3).print()
    // 2
    s.waysToReachStair(4).print()
    // 4
    s.waysToReachStair(5).print()
}

class SolutionD {

    fun waysToReachStair(k: Int): Int {

        val seen = HashMap<String, Int>()

        fun dfs(cur: Int, step: Int, minus: Boolean): Int {
            val key = "$cur, $step, $minus"
            if (key in seen) return seen[key]!!
//            println("$cur $step $minus")
            var ans = if (cur == k) 1 else 0
            if (cur > k + 1) return 0
            val pow = quickPower(2L, step.toLong()).toInt()
//            println("pow is $pow with step ${step}")
            if (minus && cur - 1 >= 0) {
                ans += dfs(cur - 1, step, false)
            }
            ans += dfs(cur + pow, step + 1, true)
            return ans.also {
                seen[key] = it
            }
        }

        return dfs(1, 0, true)
    }
}