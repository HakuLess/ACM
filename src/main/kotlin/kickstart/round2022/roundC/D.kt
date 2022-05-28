package kickstart.round2022.roundC

import utils.gcd
import utils.isPalindrome
import utils.print

// not finished
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
        val seen = HashMap<String, Long>()
        fun dfs(sb: String, cur: Long = -1): Long {
            println("$sb is $cur")
            val key = "$sb,$cur"
            if (key in seen) {
                b += seen[key]!!
                return seen[key]!!
            }

            var c = cur
            if (sb.isPalindrome() || c == -1L) {
                c++
            }
            if (sb.isEmpty()) {
                b += c
                return c
            }
            val next = StringBuilder()
            var ans = 0L
            for (i in sb.indices) {
                next.clear()
                next.append(sb.substring(0, i))
                next.append(sb.substring(i + 1, sb.length))
                ans += dfs(next.toString(), c)
            }
            return ans.also {
                seen[key] = it
//                println("$key is $it")
            }
        }
        dfs(str)
        val mod = 1000000007L
//        println("$a $b")
        val gcd = gcd(a, b)
        a /= gcd
        b /= gcd
        while (b % a != 0L) {
            b += mod
        }
        println("Case #${it + 1}: ${b / a}")
    }
}