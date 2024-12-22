package leetcode.contest.c429

import utils.biMin
import utils.print

fun main() {
    val s = SolutionC()
    // 1
//    s.minLength("000001", 1).print()
//
//    s.minLength("1101", 1).print()
    // 1
    s.minLength("00100", 2).print()
//    s.minLength("0101", 0).print()
    // 2
    s.minLength("01100011", 1).print()
    // 2
    s.minLength("1001", 1).print()

    // 2
    s.minLength("0000", 1).print()
}

class SolutionC {
    fun minLength(s: String, numOps: Int): Int {

        val n = s.length

        // 检查是否可以通过翻转使最终字符串交替排列
        fun check(x: Int): Boolean {
            var cnt = 0
            for (i in 0 until n) {
                if ((s[i].digitToInt() % 2) != ((i + x) % 2)) {
                    cnt++
                }
            }
            return cnt <= numOps
        }

        // 如果可以直接交替排列，则答案为 1
        if (check(0) || check(1)) return 1

        fun canAvoidLongRun(lim: Long): Boolean {
            var cnt = 0L
            var j = 0
            for (i in 0 until n) {
                if (i == n - 1 || s[i] != s[i + 1]) {
                    val len = i - j + 1
                    cnt += len / (lim + 1)
                    j = i + 1
                }
            }
            return cnt <= numOps
        }

        return biMin(2L, n.toLong()) {
            canAvoidLongRun(it)
        }.toInt()
    }
}

fun biMin(l: Long = 1, r: Long = Long.MAX_VALUE / 2, func: (mid: Long) -> Boolean): Long {
    var left = l
    var right = r
    while (left + 1 < right) {
        val mid = (left + right).ushr(1)
        when {
            func(mid) -> right = mid
            else -> left = mid
        }
    }
    return when {
        func(left) -> left
        func(right) -> right
        else -> -1
    }
}