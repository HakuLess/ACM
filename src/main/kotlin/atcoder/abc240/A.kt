package atcoder.abc240

import kotlin.math.abs

fun main(args: Array<String>) {
    val (a, b) = readLine()!!.trim().split(" ").map { it.toInt() }
    if (abs(a - b) == 1 || (a == 10 && b == 1) || (a == 1 && b == 10)) {
        println("Yes")
    } else {
        println("No")
    }
}