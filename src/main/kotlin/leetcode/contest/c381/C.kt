package leetcode.contest.c381

import utils.print

fun main() {
    val s = SolutionC()
    s.minimumPushes("xyzxyzxyzxyz").print()
}

class SolutionC {
    fun minimumPushes(word: String): Int {
        val map = HashMap<Char, Int>()
        word.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        val st = map.values.sortedDescending()
//        st.joinToString().print()
        var ans = 0
        for (i in st.indices) {
            val item = st[i]
            ans += item * ((i + 8) / 8)
        }

        return ans
    }
}