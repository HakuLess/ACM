package codeforces.kotlinhero.practice9

import utils.print

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.trim().split(" ").map { it.toLong() }
    val k = n.countOneBits()
    val arr = n.toString(2).map { it - '0' + 0L }.toLongArray()
//    arr.print()
//    println("$m $k $n")
    if (m !in k..n) {
        println("NO")
        return
    }
    var left = m - k
    var i = 0
    while (left != 0L) {
//        arr.print()
        if (arr[i] <= left) {
            left -= arr[i]
            arr[i + 1] = 2 * arr[i]
            arr[i] = 0
        } else {
            arr[i] -= left
            arr[i + 1] = left * 2
            left = 0L
        }
        i++
    }

    println("YES")
    val ans = ArrayList<Long>()
    var cur = 1L
    for (i in arr.indices.reversed()) {
        for(i in 0 until arr[i]) {
            ans.add(cur)
        }
        cur *= 2
    }
    println(ans.joinToString(" "))
}