package leetcode.contest.c458

import utils.print

fun main() {
    val s = SolutionA()
    s.processStr("a#b%*").print()
    s.processStr("z*#").print()
}

class SolutionA {
    fun processStr(s: String): String {
        var sb = StringBuilder()
        s.forEach {
            when (it) {
                '*' -> {
                    if (sb.isNotEmpty()) {
                        sb = StringBuilder(sb.dropLast(1))
                    }
                }
                '#' -> {
                    sb.append(sb)
                }
                '%' -> {
                    sb.reverse()
                }
                else -> {
                    sb.append(it)
                }
            }
//            println(sb.toString())
        }
        return sb.toString()
    }
}