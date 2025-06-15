package leetcode.contest.c454

import utils.print

fun main() {
    val s = SolutionA()
    s.generateTag("Bold apple beyond bright future crash mountains glow light gently dance waits shore breeze mind ")
        .print()
}

class SolutionA {
    fun generateTag(caption: String): String {
        return caption.split(' ').filter { it.isNotEmpty() }.mapIndexed { index, it ->
            println("$index: $it")
            val sb = StringBuilder()
            if (index != 0) {
                sb.append(it[0].uppercase())
            } else {
                sb.append(it[0].lowercase())
            }
            sb.append(it.substring(1).lowercase())
            sb
        }.joinToString(prefix = "#", separator = "").take(100)
    }
}