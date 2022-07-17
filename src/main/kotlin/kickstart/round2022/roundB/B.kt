package kickstart.round2022.roundB

import utils.isPalindrome

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val a = readLine().toString().toLong()
        var ans = 0
        var cur = 1L
        while (cur * cur <= a) {
            if (a % cur == 0L) {
                if (cur.toString().isPalindrome()) ans++
                if ((a / cur).toString().isPalindrome() && cur * cur != a) ans++
            }
            cur++
        }
        println("Case #${it + 1}: $ans")
    }
}