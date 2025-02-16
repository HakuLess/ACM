package leetcode.contest.b150

import utils.kmpSearch
import utils.print
import java.util.*

fun main() {
    val s = SolutionD()
//    s.shortestMatchingSubstring("abaacbaecebce", "ba*c*ce").print()
//    s.shortestMatchingSubstring("baccbaadbc", "cc*baa*adb").print()
//    s.shortestMatchingSubstring("a", "**").print()
//    s.shortestMatchingSubstring("madlogic", "*adlogi*").print()
//
//    s.shortestMatchingSubstring("jyhpbbpund", "bbp*un*").print()

    // 8
//    s.shortestMatchingSubstring("qwaxulodslq", "ax*lod*sl").print()


    // 11
//    s.shortestMatchingSubstring("uwkpnqhynsedqqgdw", "k**edq").print()
    // 2
//    s.shortestMatchingSubstring("cvtrmfmvuhzncqffl", "fl**").print()

    // 11
    s.shortestMatchingSubstring("rmyfljdvwofbf", "rmyfl**f").print()
    // 14
    s.shortestMatchingSubstring("dkmxbkkekcfxgtekzro", "mxbk*x*ek").print()
}

class SolutionD {
    fun shortestMatchingSubstring(s: String, p: String): Int {

        val str = p.replace("**", "*").trim('*')

        if (str.isEmpty()) return 0

        if (!str.contains('*')) {
            if (s.contains(str)) return str.length
        }

        if (str.count { it == '*' } == 1) {
            val a = TreeSet<Int>()
            val b = TreeSet<Int>()

            str.split("*").forEachIndexed { index, it ->
                val target = when (index) {
                    0 -> a
                    else -> b
                }
                kmpSearch(it, s) {
                    target.add(it)
                }
            }

            val size = str.split('*')

//            a.print()
//            b.print()

            var ans = Int.MAX_VALUE
            b.forEach {
                val left = a.floor(it - size[0].length)

//                println("$it: $left")

                if (left != null) {
                    ans = minOf(ans, it - left + size[1].length)
                }
            }

            if (ans == Int.MAX_VALUE) ans = -1
            return ans
        }

        val a = TreeSet<Int>()
        val b = TreeSet<Int>()
        val c = TreeSet<Int>()

        str.split("*").forEachIndexed { index, it ->
            val target = when (index) {
                0 -> a
                1 -> b
                else -> c
            }
            kmpSearch(it, s) {
                target.add(it)
            }
        }

//        a.print()
//        b.print()
//        c.print()

        val size = p.split('*')

        var ans = Int.MAX_VALUE
        b.forEach {
            val left = a.floor(it - size[0].length)
            val right = c.ceiling(it + size[1].length)

//            println("$it : $left $right ")
            if (left != null && right != null) {
                ans = minOf(ans, right - left + size[2].length)
            }
        }

        if (ans == Int.MAX_VALUE) ans = -1
        return ans
    }
}