package kickstart.round2022.roundC

import utils.gcd
import utils.isPalindrome
import utils.print

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        readLine().toString()
        val str = readLine().toString()
        var a = 1L
        for (i in 1..str.length) {
            a *= i
        }
        var b = 0L
        fun dfs(sb: String) {
            if (sb.isPalindrome()) {
                b++
            }
            val next = StringBuilder()
            for (i in sb.indices) {
                next.clear()
                next.append(sb.substring(0, i))
                next.append(sb.substring(i + 1, sb.length))
                dfs(next.toString())
            }
        }
        dfs(str)
        val mod = 1000000007L
        val gcd = gcd(a, b)
        a /= gcd
        b /= gcd
        while (b % a != 0L) {
            b += mod
        }
        println("Case #${it + 1}: ${b / a}")
    }
}