package codeforces.round764

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    repeat(n) {
        val (a, b, c) = readLine()!!.split(" ").map { it.toInt() }
        if ((2 * b - c) % a == 0 && (2 * b - c) / a > 0) {
            println("YES")
        } else if ((2 * b - a) % c == 0 && (2 * b - a) / c > 0) {
            println("YES")
        } else if ((a + c) % (2 * b) == 0 && (a + c) / (2 * b) > 0) {
            println("YES")
        } else {
            println("NO")
        }
    }
}