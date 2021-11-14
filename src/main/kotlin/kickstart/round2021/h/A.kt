package kickstart.round2021.h

import kotlin.math.abs

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val s = readLine().toString()
        val f = readLine().toString()
        var ans = 0
        s.forEach {
            var min = 13
            f.forEach { c ->
                val cur = minOf(abs(it - c), 26 - abs(it - c))
                min = minOf(min, cur)
            }
            ans += min
        }
        println("Case #${it + 1}: $ans")
    }
}