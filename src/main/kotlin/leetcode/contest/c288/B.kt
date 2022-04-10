package leetcode.contest.c288

import utils.print

fun main() {
    val s = SolutionB()
//    s.minimizeResult("247+38").print()
//    s.minimizeResult("12+34").print()
//    s.minimizeResult("999+999").print()
    s.minimizeResult("1+1").print()
}

class SolutionB {
    fun minimizeResult(expression: String): String {
        val index = expression.indexOf('+')
        val l = expression.length
        var ans = Long.MAX_VALUE
        var res = ""
        for (i in 0 until index) {
            val a = expression.substring(0, i).toLongOrNull() ?: 1L
            val b = expression.substring(i, index).toLongOrNull() ?: 1L
//            println("a $a b $b")
            for (j in index + 1..l) {
                val c = expression.substring(index + 1, j).toLongOrNull() ?: 1L
                val d = expression.substring(j, l).toLongOrNull() ?: 1L
//                println("c $c d $d")
                val cur = a * (b + c) * d
                if (cur <= ans) {
                    ans = cur
                    res = "${expression.substring(0, i)}(${
                        expression.substring(
                            i,
                            index
                        )
                    }+${expression.substring(index + 1, j)})${expression.substring(j, l)}"
                }
            }
        }
        return res
    }
}