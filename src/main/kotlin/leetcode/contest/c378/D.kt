package leetcode.contest.c378

import utils.BitTree
import utils.SegmentTree
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
//    s.canMakePalindromeQueries("abcabc", "[[1,1,3,5],[0,2,5,5]]".toGrid()).print()
    // true
    s.canMakePalindromeQueries("acbcab", "[[1,2,4,5]]".toGrid()).print()
    // false
    s.canMakePalindromeQueries("odaxusaweuasuoeudxwa", "[[0,5,10,14]]".toGrid()).print()
}

class SolutionD {
    fun canMakePalindromeQueries(s: String, queries: Array<IntArray>): BooleanArray {

        val n = s.length

        fun check(a: Int, b: Int): Boolean {
            return s.substring(IntRange(a, b)).toCharArray().sorted().joinToString() == s.substring(
                IntRange(
                    n - 1 - b,
                    n - 1 - a
                )
            ).toCharArray().sorted().joinToString()
        }

        val left = ArrayList<Char>()
        val right = ArrayList<Char>()
        for (i in s.indices) {
            if (i < n / 2) {
                left.add(s[i])
            } else {
                right.add(s[i])
            }
        }
        if (left.sorted().joinToString() != right.sorted().joinToString())
            return BooleanArray(queries.size) {
                false
            }

        val root = SegmentTree<Int>(start = 0, end = 100005, value = 0) { a, b ->
            a + b
        }

        var cover = 0
        for (i in 0 until n / 2) {
            if (s[i] != s[n - 1 - i]) {
//                println("update $i")
                root.update(root, i, 1)
                root.update(root, n - 1 - i, 1)
                cover += 1
            }
        }

//        println("$n: cover $cover")

        val ans = ArrayList<Boolean>()
        queries.forEach {
            val (a, b, c, d) = it
            val e = n - 1 - d
            val f = n - 1 - c
//            println("$a..$b with $e..$f")
//            s.substring(IntRange(a, b)).print()

            // 无相交部分
            if (e > b || f < a) {
//                println("add top")
//                println("$a..$b with ${root.query(root, a, b)}")
//                println("$e..$f with ${root.query(root, e, f)}")
                if (root.query(root, a, b) + root.query(root, e, f) == cover) {
//                    println("left ${s.substring(IntRange(e, f))} right ${s.substring(IntRange(c, d))}")
                    if (check(c, d))
                        ans.add(true)
                    else
                        ans.add(false)
                } else {
                    ans.add(false)
                }
            } else {
                // 有相交部分
                println("add bottom")

                println("$a..$b $e..$f")
                if (a <= e && b >= f) {
                    // 完全包括
                    ans.add(root.query(root, a, b) == cover)
                } else if (e <= a && f >= b) {
                    // 完全包括
                    ans.add(root.query(root, e, f) == cover)
                } else {
                    val arr = arrayOf(a, b, e, f).sorted()
                    val (a0, b0, e0, f0) = arr

                    println("check ${root.query(root, a0, e0 - 1)} with ${root.query(root, e0, f0)} compare $cover")
                    if (root.query(root, a0, e0 - 1) + root.query(root, e0, f0) == cover) {
                        ans.add(true)
                    } else {
                        ans.add(false)
                    }
                }
            }
        }
        return ans.toBooleanArray()
    }
}