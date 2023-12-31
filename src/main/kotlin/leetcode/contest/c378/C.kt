package leetcode.contest.c378

import utils.print

fun main() {
    val s = SolutionC()
//    s.maximumLength("aaaa").print()
//    s.maximumLength("abcdef").print()
//    s.maximumLength("abcaba").print()
    // 5
    s.maximumLength("abbbbbggggggyyyggggg").print()
    // 6
    s.maximumLength("iiiiifffffffoooookkkfffffffnnxxxxxx").print()
}

class SolutionC {
    fun maximumLength(s: String): Int {

        val map = HashMap<Char, ArrayList<Int>>()
        for (i in 'a'..'z') {
            map[i] = arrayListOf()
        }

        var c = 0
        for (i in s.indices) {
            c++
            if ((i + 1 in s.indices && s[i] != s[i + 1]) || i + 1 !in s.indices) {
                map[s[i]]!!.add(c)
                c = 0
            }
        }
        var ans = -1
        map.values.forEach {
            if (it.isEmpty()) return@forEach
            it.sortDescending()
            ans = maxOf(ans, it[0] - 2)
            if (it.size >= 3) {
                ans = maxOf(ans, it[2])
            }
            if (it.sum() >= 3) {
                ans = maxOf(ans, 1)
            }
            if (it.size >= 2) {
                if (it[0] > it[1])
                    ans = maxOf(ans, it[1])
                else
                    ans = maxOf(ans, it[1] - 1)
            }
        }

        if (ans == 0) return -1
        return ans
    }
}