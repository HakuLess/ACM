package kickstart.round2020.b

import java.util.*

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val s = readLine()!!.trim()
        val stc = Stack<Long>()
        val stn = Stack<LongArray>()
        stn.push(longArrayOf(1L, 1L))
        s.forEach {
            if (it in '1'..'9') {
                stc.push((it - '0').toLong())
            } else if (it == ')') {
                val c = stc.pop()
                val p = stn.pop()
                stn.peek()[0] += c * p[0]
                stn.peek()[0] %= 1000000000L
                stn.peek()[1] += c * p[1]
                stn.peek()[1] %= 1000000000L
            } else if (it == '(') {
                stn.push(longArrayOf(0L, 0L))
            } else {
                when (it) {
                    'S' -> stn.peek()[1]++
                    'E' -> stn.peek()[0]++
                    'N' -> stn.peek()[1]--
                    'W' -> stn.peek()[0]--
                }
            }
        }

        println(
            "Case #${it + 1}: ${
                stn.peek().joinToString(separator = " ") {
                    (it % 1000000000L).let {
                        if (it <= 0) {
                            1000000000L + it
                        } else {
                            it
                        }
                    }.toString()
                }
            }"
        )
    }
}