package leetcode.contest.b59

import utils.print

fun main() {
    val s = Solution5837()
//    s.numberOfCombinations("327").print()
    s.numberOfCombinations("9999999999999").print()
    val str = StringBuilder()
    repeat(1000) {
        str.append('9')
    }
    s.numberOfCombinations(str.toString()).print()

    // 8 49 69
    // 84 969
    // 84969
    // 8 4969
//    s.numberOfCombinations("84969").print()
}

class Solution5837 {
    fun numberOfCombinations(num: String): Int {
        if (num.startsWith('0')) return 0
        val mod = 1000000007L
        val seen = HashMap<String, Long>()
        fun dfs(cur: String, lst: String, index: Int): Long {
//            println("${cur.length}  lst ${lst.length}")
            val key = "$cur,$index"
            if (key in seen) return seen[key]!!
            if (lst.isEmpty()) return 1L
            if (lst.first() == '0') return 0L
            if (lst.length < cur.length) return 0L
            if (cur > lst && cur.length == lst.length) return 0L
            var ans = 1L
            for (i in cur.length until lst.length) {
                if (i != cur.length || lst.substring(0, i) >= cur) {
                    if (lst.length - i < i) continue
                    ans += dfs(lst.substring(0, i), lst.substring(i, lst.length), index + i)
                    ans %= mod
                }
            }
            return ans.also {
                seen[key] = it
            }
        }

        var ans = 1L
        for (i in 1 until num.length / 2 + 1) {
            ans += dfs(num.substring(0, i), num.substring(i, num.length), i)
        }
        return (ans % mod).toInt()
    }
}