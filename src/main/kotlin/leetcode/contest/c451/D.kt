package leetcode.contest.c451

import utils.print
import java.util.*
import kotlin.collections.HashSet
import kotlin.math.abs
import kotlin.system.measureTimeMillis

fun main() {
    val s = SolutionD()
    measureTimeMillis {
        s.lexicographicallySmallestString("dbacfeaabeeaacbbbedcdfeabbfcffedfedeaccbcadaeffaaebecacbcfebde").print()
    }.print()

    measureTimeMillis {
        s.lexicographicallySmallestString("jijjjkjlhlhikghjghlkgikihikihkjjjlghhilkkglghlggghkljhikljkhilh").print()
    }.print()

}

class SolutionD {

//    fun lexicographicallySmallestString(s: String): String {
//
//        fun isAdjacent(a: Char, b: Char): Boolean {
//            val diff = abs(a - b)
//            return diff == 1 || diff == 25
//        }
//
//        val visited = HashSet<String>()
//        val pq = PriorityQueue<String>()
//
//        pq.add(s)
//        visited.add(s)
//
//        var ans = s
//
//        while (pq.isNotEmpty()) {
//            val cur = pq.poll()
//            ans = minOf(ans, cur)
//
//            for (i in 0 until cur.length - 1) {
//                if (isAdjacent(cur[i], cur[i + 1])) {
//                    val next = cur.removeRange(i, i + 2)
//                    if (next !in visited) {
//                        visited.add(next)
//                        pq.add(next)
//                    }
//                }
//            }
//        }
//
//        return ans
//    }

    fun lexicographicallySmallestString(s: String): String {

        fun isAdjacent(a: Char, b: Char): Boolean {
            val diff = abs(a - b)
            return diff == 1 || diff == 25
        }

        val seen = HashSet<String>()
        fun dfs(str: String, startIndex: Int): String {
            if (str in seen) return str
            seen.add(str)
//            println("enter $str")

            var minStr = str

            for (i in startIndex until str.length - 1) {
                if (isAdjacent(str[i], str[i + 1])) {
                    val next = str.removeRange(i, i + 2)
                    val tmp = dfs(next, maxOf(0, i - 1))
                    if (tmp < minStr) {
                        minStr = tmp
                    }
                }
            }

            return minStr
        }

        return dfs(s, 0)
    }
}