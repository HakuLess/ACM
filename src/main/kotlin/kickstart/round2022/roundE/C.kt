package kickstart.round2022.roundE

import utils.isPalindrome

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.trim().toInt()
        val s = readLine()!!.trim()
        var ans = s
        for (i in s.indices) {
            for (j in i + 1..s.length) {
                val sub = s.substring(i, j)
                if (sub.length < ans.length && sub.isPalindrome() && "$s$sub".isPalindrome()) {
                    ans = sub
                }
            }
        }
        println("Case #${it + 1}: $ans")
    }
}