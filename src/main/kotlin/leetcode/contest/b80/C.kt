package leetcode.contest.b80

import utils.print

fun main() {
    val s = SolutionC()
    s.matchReplacement(
        "fool3e7bar",
        "leet",
        arrayOf(charArrayOf('e', '3'), charArrayOf('t', '7'), charArrayOf('t', '8'))
    ).print()
}

class SolutionC {
    fun matchReplacement(s: String, sub: String, mappings: Array<CharArray>): Boolean {
        if (sub.length > s.length) return false
        val map = HashSet<String>()
        mappings.forEach {
            map.add("${it[0]}${it[1]}")
        }
        var l = 0
        while (l + sub.length <= s.length) {
            var ans = true
            for (i in sub.indices) {
                println("$l: $i sub is ${sub[i]}, s is ${s[i + l]}, map is ${sub[i]}${s[i + l]}")
                if (sub[i] == s[i + l]) continue
                if ("${sub[i]}${s[i + l]}" in map) continue
                ans = false
                break
            }
            if (ans) return true
            l++
        }
        return false
    }
}