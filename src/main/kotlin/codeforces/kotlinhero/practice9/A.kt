package codeforces.kotlinhero.practice9

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    repeat(n) {
        readLine()
        val arr = readLine()!!.trim().split(" ").map { it.toInt() }
        arr.toSet().forEach { target ->
            if (arr.count { it == target } == 1) {
                println(arr.indexOf(target) + 1)
                return@repeat
            }
        }
    }
}