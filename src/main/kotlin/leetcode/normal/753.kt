package leetcode.normal

import utils.print
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = Solution753()
    s.crackSafe(1, 1).print()
    s.crackSafe(1, 2).print()
    s.crackSafe(2, 2).print()
    s.crackSafe(3, 3).print()
}

// 欧拉通路
// 欧拉图
class Solution753 {
    fun crackSafe(n: Int, k: Int): String {
        if (n == 1) {
            val ans = StringBuilder()
            for (i in 0 until k) {
                ans.append(i)
            }
            return ans.toString()
        }

        val pairs = ArrayList<Pair<String, String>>()

        fun dfs(level: Int, cur: String) {
            if (level == n) {
                pairs.add(Pair(cur.substring(0, cur.length - 1), cur.substring(1, cur.length)))
                return
            }
            for (i in 0 until k) {
                dfs(level + 1, cur + i)
            }
        }

        dfs(0, "")

        val map = HashMap<String, Stack<Pair<String, String>>>()
        pairs.forEach {
            map[it.first] = map.getOrDefault(it.first, Stack())
            map[it.first]!!.push(it)
        }

        val ans = ArrayList<Pair<String, String>>()
        fun dfs0(cur: String, arr: ArrayList<Pair<String, String>>) {
            while (map[cur]?.isNotEmpty() == true) {
                val next = map[cur]?.pop() ?: return
                dfs0(next.second, arr)
                arr.add(next)
            }
        }
        dfs0(pairs[0].first, ans)
        return ans.reversed().let {
            it[0].first + it.map {
                it.second.last()
            }.joinToString("")
        }
    }
}