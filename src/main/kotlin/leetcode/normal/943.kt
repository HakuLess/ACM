package leetcode.normal

import utils.bitOne
import utils.changeBit
import utils.countOne
import utils.print

fun main() {
    val s = Solution943()
    s.shortestSuperstring(arrayOf("alex", "loves", "leetcode")).print()
    s.shortestSuperstring(arrayOf("catg", "ctaagt", "gcta", "ttca", "atgcatc")).print()
    s.shortestSuperstring(
        arrayOf(
            "cedefifgstkyxfcuajfa",
            "ooncedefifgstkyxfcua",
            "assqjfwarvjcjedqtoz",
            "fcuajfassqjfwarvjc",
            "fwarvjcjedqtozctcd",
            "zppedxfumcfsngp",
            "kyxfcuajfassqjfwa",
            "fumcfsngphjyfhhwkqa",
            "fassqjfwarvjcjedq",
            "ppedxfumcfsngphjyf",
            "dqtozctcdk"
        )
    ).print()
}

class Solution943 {
    fun shortestSuperstring(words: Array<String>): String {
        val n = words.size
        val arr = Array<Array<String>>(n) { Array(n) { "" } }
        for (i in words.indices) {
            for (j in words.indices) {
                if (i == j) continue
                var tmp = 0
                for (k in minOf(words[i].length, words[j].length) downTo 0) {
                    if (words[i].substring(words[i].length - k) == words[j].substring(0, k)) {
                        tmp = k
                        break
                    }
                }
                arr[i][j] = words[j].drop(tmp)
            }
        }

        val seen = HashMap<Int, Int>()
        var ans = ""
        fun dfs(state: Int, last: Int, cur: String) {
            val key = state + last * 50000
            if (key in seen && seen[key]!! <= cur.length) return
            seen[key] = cur.length

            if (state.countOne() == n) {
                if (ans.isEmpty() || ans.length > cur.length) {
                    ans = cur
                }
                return
            }
            for (i in 0 until n) {
                if (!state.bitOne(i)) {
                    if (last == -1) {
                        dfs(state.changeBit(i), i, cur + words[i])
                    } else {
                        dfs(state.changeBit(i), i, cur + arr[last][i])
                    }
                }
            }
        }
        dfs(0, -1, "")
        return ans
    }
}