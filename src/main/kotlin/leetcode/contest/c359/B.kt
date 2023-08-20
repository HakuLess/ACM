package leetcode.contest.c359

import utils.print

fun main() {
    val s = SolutionB()
    s.minimumSum(2, 3).print()
    s.minimumSum(3, 5).print()
    // 13   1 2 3 6
    s.minimumSum(4, 7).print()
}

class SolutionB {
    fun minimumSum(n: Int, k: Int): Int {
        val l = arrayListOf<Int>()
        for (i in 1..n) {
            l.add(i)
        }
        var repeat = true
        while (repeat) {
            repeat = false
            for (i in l.indices) {
                if (k - l[i] != l[i] && k - l[i] in l) {
                    println("add ${l.last() + 1}")
                    l.add(l.last() + 1)

                    println("remove ${k - l[i]}")
                    l.remove(maxOf(k - l[i], l[i]))

                    repeat = true
                }
            }
        }
        return l.sum()
    }
}