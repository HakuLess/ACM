package codeforces.round764

import kotlin.collections.HashMap

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        val str = readLine()!!
        val map = HashMap<Char, Int>()
        str.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        var a = 0
        var b = 0
        for (i in 'a'..'z') {
            // 可以有多少对
            a += map.getOrDefault(i, 0) / 2
            // 可以有多少余量
            b += map.getOrDefault(i, 0) % 2
        }
//        println("$a $b ${a - a / k * k}")
        println("${a / k * 2 + if (b + 2 * (a - a / k * k) >= k) 1 else 0}")
    }
}