package leetcode.contest.c276

import utils.print

fun main() {
    val s = SolutionA()
    s.divideString("abcdefghi", 3, 'x').joinToString().print()
    s.divideString("abcdefghij", 3, 'x').joinToString().print()
}

class SolutionA {
    fun divideString(s: String, k: Int, fill: Char): Array<String> {
        val arr = ArrayList<String>()
        var cur = 0
        while (cur * k in s.indices) {
            val a = s.substring(cur * k, minOf(s.length, (cur + 1) * k))
            arr.add(a)
            cur++
        }
        while (arr.last().length != k) {
            arr[arr.lastIndex] = arr[arr.lastIndex] + fill
        }
        return arr.toTypedArray()
    }
}