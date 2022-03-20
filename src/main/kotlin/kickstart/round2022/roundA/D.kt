package kickstart.round2022.roundA

import utils.gcd

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (a, b) = readLine()!!.split(" ").map { it.toLong() }
        var ans = 0
        for (i in a..b) {
            var sum = 0L
            i.toString().forEach {
                sum += it - '0'
            }

            var tmp = sum
            i.toString().forEach {
                tmp /= gcd(tmp, 0L + (it - '0'))
            }
            if (tmp == 1L) {
//                println("$i is p")
                ans++
            }
        }
        println("Case #${it + 1}: $ans")
    }
}