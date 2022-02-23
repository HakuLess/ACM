package codeforces.kotlinhero.practice9

import utils.print

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    val s = readLine()!!.map { it - 'a' }.toIntArray()
    val t = readLine()!!.map { it - 'a' }.toIntArray()

    val ans = IntArray(n)
    for (i in s.indices.reversed()) {
        ans[i] += s[i] + t[i]
        if (ans[i] / 26 > 0 && i != 0) {
            ans[i] %= 26
            ans[i - 1]++
        }
    }

    var add = 0
    for (i in ans.indices) {
        if ((ans[i] + add) % 2 == 0) {
            ans[i] = (ans[i] + add) / 2
            add = 0
        } else {
            ans[i] = (ans[i] + add) / 2
            add = 26
        }
    }

//    println(ans.joinToString())
    println(ans.map { 'a' + it }.joinToString(""))
}