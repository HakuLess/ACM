package leetcode.contest.b124

import utils.print

fun main() {
    val s = SolutionB()
    s.lastNonEmptyString("aabcbbca").print()
}

class SolutionB {
    fun lastNonEmptyString(s: String): String {
        val map = HashMap<Char, ArrayList<Int>>()
        var max = 0
        for (i in s.indices) {
            map[s[i]] = map.getOrDefault(s[i], arrayListOf())
            map[s[i]]!!.add(i)
            max = maxOf(max, map[s[i]]!!.size)
        }
        val l = ArrayList<Pair<Char, Int>>()
        for (i in 'a'..'z') {
            val list = map.getOrDefault(i, arrayListOf())
            if (list.size == max) {
                l.add(Pair(i, list.last()))
            }
        }
        return l.sortedBy { it.second }.map { it.first.toString() }.joinToString("")
    }
}