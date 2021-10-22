package kickstart.round2021.b

import utils.print

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        readLine()
        val s = readLine()!!.trim()
        val ans = ArrayList<Int>()
        for (i in s.indices) {
            var cur = 1
            for (j in i - 1 downTo 0) {
                if (s[j + 1] > s[j]) {
                    cur++
                } else {
                    break
                }
            }
            ans.add(cur)
        }
        println("Case #${it + 1}: ${ans.joinToString(" ")}")
    }
}