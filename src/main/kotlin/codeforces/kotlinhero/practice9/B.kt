package codeforces.kotlinhero.practice9

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    val s = readLine()!!
    val ans = StringBuilder()
    var index = 0
    var step = 1
    while (index in s.indices) {
        ans.append(s[index])
        index += step
        step++
    }
    println(ans)
}