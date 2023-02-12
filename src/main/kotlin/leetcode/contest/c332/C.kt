package leetcode.contest.c332

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.substringXorQueries("101101", "[[0,5],[1,2]]".toGrid()).print()
}

class SolutionC {
    fun substringXorQueries(s: String, queries: Array<IntArray>): Array<IntArray> {
        val ans = ArrayList<IntArray>()
        val map = HashMap<Int, IntArray>()
        for (j in 30 downTo 1) {
            for (i in s.indices.reversed()) {
                if (i + j > s.length) continue
                val sub = s.substring(i, i + j)
                val v = sub.toInt(2)
                map[v] = intArrayOf(i, i + sub.length - 1)
            }
        }

        queries.forEach {
            val (a, b) = it
            val target = a xor b
            val v = map.getOrDefault(target, intArrayOf(-1, -1))
            ans.add(v)
        }
        return ans.toTypedArray()
    }
}