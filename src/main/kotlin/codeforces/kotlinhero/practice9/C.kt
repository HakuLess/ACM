package codeforces.kotlinhero.practice9

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    val arr = readLine()!!.trim().split(" ").map { it.toInt() }.sorted()
    var ans = 0
    for (i in arr.indices step 2) {
        ans += arr[i + 1] - arr[i]
    }
    println(ans)
}