package codeforces.round764

// 二分，每次将中值匹配到n的正整数倍上
fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    var left = 1
    var right = n - 1
    while (left != right) {
        val mid = (left + right) / 2
        var diff = (n - mid - 1)
        while (diff < 0) diff += n
        println("+ $diff")
        val res = readLine()!!.toInt()
        if (res != left / n) {
            left = mid + 1 + diff
            right += diff
        } else {
            left += diff
            right = mid + diff
        }
    }
    println("! $left")
}