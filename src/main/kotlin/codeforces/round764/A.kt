package codeforces.round764

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    repeat(n) {
        readLine()
        val arr = readLine()!!.split(" ").map { it.toInt() }
        println(arr.maxOrNull()!! - arr.minOrNull()!!)
    }
}