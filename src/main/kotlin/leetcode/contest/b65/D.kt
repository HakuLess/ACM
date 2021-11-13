package leetcode.contest.b65

import utils.print
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = SolutionD()
    s.maxTaskAssign(intArrayOf(5, 9, 8, 5, 9), intArrayOf(1, 6, 4, 2, 6), 1, 5).print()
}

class SolutionD {
    // 二分，算isValid
    fun maxTaskAssign(tasks: IntArray, workers: IntArray, pills: Int, strength: Int): Int {
        tasks.sortDescending()

        val tm = TreeMap<Int, Int>()
        workers.forEach {
            tm[it] = tm.getOrDefault(it, 0) + 1
        }

        val tmp = ArrayList<Int>()
        val seen = HashMap<String, Int>()
        fun dfs(i: Int, lp: Int): Int {
            val seenKey = "$i,$lp,${tmp.joinToString()}"
            if (seenKey in seen) return seen[seenKey]!!

            if (i !in tasks.indices) return 0
            var ans = 0

            val key = tm.ceilingKey(tasks[i])
            val keyP = tm.ceilingKey(tasks[i] - strength)

            if (key != null) {
                // 直接比这个大
                tm[key] = tm.getOrDefault(key, 0) - 1
                if (tm[key] == 0) tm.remove(key)
                ans = maxOf(ans, 1 + dfs(i + 1, lp))
                tm[key] = tm.getOrDefault(key, 0) + 1
            }
            if (keyP != null && lp != 0) {
                // 用药片比这个大
                tm[keyP] = tm.getOrDefault(keyP, 0) - 1
                if (tm[keyP] == 0) tm.remove(keyP)
                tmp.add(keyP)
                ans = maxOf(ans, 1 + dfs(i + 1, lp - 1))
                tmp.remove(keyP)
                tm[keyP] = tm.getOrDefault(keyP, 0) + 1
            }
            // 不做这个任务
            ans = maxOf(ans, dfs(i + 1, lp))
            return ans.also {
                seen[seenKey] = it
            }
        }

        return dfs(0, pills)
    }
}