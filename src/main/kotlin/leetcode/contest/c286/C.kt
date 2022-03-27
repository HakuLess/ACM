package leetcode.contest.c286

import utils.print

fun main() {
    val s = SolutionC()
//    s.kthPalindrome(intArrayOf(1, 2, 3, 4, 5, 90), 3).print()
    s.kthPalindrome(intArrayOf(72, 90), 3).print()
//    s.kthPalindrome(intArrayOf(2, 4, 6), 4).print()
//    s.kthPalindrome(intArrayOf(2, 201429812, 8, 520498110, 492711727, 339882032, 462074369, 9, 7, 6), 1).print()
}

class SolutionC {
    fun kthPalindrome(queries: IntArray, intLength: Int): LongArray {
        if (intLength == 1) {
            return queries.map { if (it <= 9) it.toLong() else -1L }.toTypedArray().toLongArray()
        }
        if (intLength % 2 == 0) {
            var s = 1L
            repeat(intLength / 2 - 1) {
                s *= 10L
            }
            return queries.map {
                if (it.toString().length > intLength) -1L else {
                    val l = (it + s - 1).toString()
                    (l + l.reversed()).toLongOrNull() ?: -1L
                }
            }.toTypedArray().toLongArray().map {
                if (it.toString().length != intLength) {
                    -1
                } else {
                    it
                }
            }.toLongArray()
        } else {
            var s = 1L
            repeat(intLength / 2 - 1) {
                s *= 10L
            }
            return queries.map {
                if (it.toString().length > intLength) -1L else {
                    val c = it / 10 + s
                    val l = (if (it % 10 == 0) c - 1 else c).toString()
                    val o = it - 1
                    val mid = (o % 10).toString()
                    (l + mid + l.reversed()).toLongOrNull() ?: -1L
                }
            }.toTypedArray().toLongArray().map {
                if (it.toString().length != intLength) {
                    -1
                } else {
                    it
                }
            }.toLongArray()
        }
    }
}